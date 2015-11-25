package net.hoyoung.weibospider.api.controller;

import net.hoyoung.weibospider.api.common.MessageQueue;
import net.hoyoung.weibospider.api.vo.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/25.
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private MessageQueue messageQueue;
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> get(){
        logger.info("客户端取消息");
        List<Message> list = messageQueue.pollAll(5);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("status",0);
        map.put("messages",list);
        return map;
    }
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> test(){
        Message message = new Message("ERROR","500","测试消息队列"+Math.random());
        messageQueue.put(message);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ok",1);
        return map;
    }
}
