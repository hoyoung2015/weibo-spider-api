package net.hoyoung.weibospider.api.executer;

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
    private boolean running = false;
    @Autowired
    SystemContext systemContext;
    @PostConstruct
    public void start(){
        logger.info(">>>>>>>>>>>>>>>>>>>>>start");
        running = true;
        Timeline timeline = new Timeline(systemContext.ACCESS_TOCKEN);
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
        }
    }

    public WeiboSpiderExecuter() {

    }
}
