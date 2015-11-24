package net.hoyoung.weibospider.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hoyoung on 2015/11/23.
 */
@Component
public class SystemContext {
    @Value("${access_token}")
    public String ACCESS_TOCKEN;
    @Value("${spider.enable}")
    public boolean SPIDER_ENABLE;
    @Value("${spider.sleepTime}")
    public int SPIDER_SLEEP_TIME;
    @Value("${spider.pageSize}")
    public int SPIDER_PAGE_SIZE;
}
