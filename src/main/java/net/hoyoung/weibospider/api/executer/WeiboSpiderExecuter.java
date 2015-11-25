package net.hoyoung.weibospider.api.executer;

import net.hoyoung.weibospider.api.common.MessageQueue;
import net.hoyoung.weibospider.api.common.SystemContext;
import net.hoyoung.weibospider.api.service.StatusService;
import net.hoyoung.weibospider.api.vo.BaseStatus;
import net.hoyoung.weibospider.api.vo.Message;
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
    private MessageQueue messageQueue;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private StatusService statusService;
    @Autowired
    SystemContext systemContext;
    private DaeMonThread daeMonThread;

    private long totalDoc;//总的公共微博数量

    public long getTotalDoc() {
        return totalDoc;
    }

    public BaseStatus start(){

        if(daeMonThread!=null && daeMonThread.isAlive()){
            logger.info("守护线程已经启动");
            return new BaseStatus(true,"抓取执行器已经开启");
        }
        logger.info("创建守护线程");
        daeMonThread = new DaeMonThread();
        daeMonThread.start();
        totalDoc = 0;
        logger.info("抓取执行器开始抓取");
        return new BaseStatus(true,"抓取执行器开始抓取");
    }
    public BaseStatus stop(){

        if(daeMonThread==null || !daeMonThread.isAlive()){
            logger.info("线程没有在运行");
            return new BaseStatus(false,"线程没有在运行");
        }
        daeMonThread.interrupt();
        logger.info("抓取执行器停止");
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
                            WeiboSpiderExecuter.this.totalDoc++;
                            statusService.saveOrUpdate(doc);
                        }
                    }
                    Thread.sleep(WeiboSpiderExecuter.this.systemContext.SPIDER_SLEEP_TIME);
                }catch (InterruptedException  e){
                    logger.info(e.getMessage());
                    logger.info("从阻塞中退出..."+isInterrupted());
                } catch (WeiboException e) {
                    Message message = new Message("ERROR","500",e.getMessage());
                    WeiboSpiderExecuter.this.messageQueue.put(message);

                    logger.error(e.getMessage());
                    this.interrupt();
                    e.printStackTrace();
                }
            }
            logger.info("守护线程结束...");
        }
    }
}
