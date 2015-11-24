package net.hoyoung.weibospider.api.executer;

import net.hoyoung.weibospider.api.BaseStatus;
import net.hoyoung.weibospider.api.SystemContext;
import net.hoyoung.weibospider.api.service.StatusService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

/**
 * Created by hoyoung on 2015/11/23.
 */
@Component
public class WeiboSpiderExecuter {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private StatusService statusService;
    @Autowired
    SystemContext systemContext;
    private DaeMonThread daeMonThread = new DaeMonThread();
    public boolean start(){
        logger.info("抓取执行器开始抓取");
        /*Timeline timeline = new Timeline(systemContext.ACCESS_TOCKEN);
        while (systemContext.SPIDER_ENABLE){
            try {
                StatusWapper status = timeline.getPublicTimeline(200,0);
                if(status!=null&&status.getStatuses()!=null&&status.getStatuses().size()>0){
                    for (Status doc : status.getStatuses()){
                        statusService.saveOrUpdate(doc);
                    }
                }
            } catch (WeiboException e) {
                e.printStackTrace();
            }
            try {
                logger.info("守护线程工作中");
                Thread.sleep(systemContext.SPIDER_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        daeMonThread.start();
        return true;
    }
    public BaseStatus stop(){
        logger.info("抓取执行器停止");
        if(!daeMonThread.isAlive()){
            return new BaseStatus(false,"线程没有在运行");
        }
        daeMonThread.interrupt();
        return new BaseStatus(true,"线程成功停止");
    }
    public WeiboSpiderExecuter() {

    }
    class DaeMonThread extends Thread{
        private boolean isInterrupted=false;

        @Override
        public void interrupt() {
            isInterrupted = true;
            super.interrupt();
        }

        @Override
        public void run() {
            while (WeiboSpiderExecuter.this.systemContext.SPIDER_ENABLE && !isInterrupted){
                try{
                    logger.info("守护线程工作中...");
                    Timeline timeline = new Timeline(WeiboSpiderExecuter.this.systemContext.ACCESS_TOCKEN);
                    StatusWapper status = timeline.getPublicTimeline(WeiboSpiderExecuter.this.systemContext.SPIDER_PAGE_SIZE,0);
                    if(status!=null&&status.getStatuses()!=null&&status.getStatuses().size()>0){
                        for (Status doc : status.getStatuses()){
                            statusService.saveOrUpdate(doc);
                        }
                    }
                    Thread.sleep(WeiboSpiderExecuter.this.systemContext.SPIDER_SLEEP_TIME);
                }catch (InterruptedException  e){
                    logger.info(e.getMessage());
                    logger.info("从阻塞中退出..."+isInterrupted());
                } catch (WeiboException e) {
                    e.printStackTrace();
                }
            }
            logger.info("守护线程结束...");
        }
    }
}
