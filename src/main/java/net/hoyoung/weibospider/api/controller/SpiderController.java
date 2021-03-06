package net.hoyoung.weibospider.api.controller;

import net.hoyoung.weibospider.api.executer.WeiboSpiderExecuter;
import net.hoyoung.weibospider.api.vo.BaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/24.
 */
@RequestMapping("spider")
public class SpiderController {
    @Autowired
    private WeiboSpiderExecuter  weiboSpiderExecuter;
    @RequestMapping(value = "start",method = RequestMethod.GET)
    public @ResponseBody BaseStatus start(){

        return weiboSpiderExecuter.start();
    }
    @RequestMapping(value = "stop",method = RequestMethod.GET)
    public @ResponseBody BaseStatus stop(){
        BaseStatus baseStatus = weiboSpiderExecuter.stop();
        return baseStatus;
    }
    @RequestMapping(value = "state",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> state(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("totalDoc",weiboSpiderExecuter.getTotalDoc());
        return map;
    }
}
