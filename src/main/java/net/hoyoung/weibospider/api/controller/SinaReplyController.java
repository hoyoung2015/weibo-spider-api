package net.hoyoung.weibospider.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hoyoung on 2015/11/23.
 */
@Controller
@RequestMapping("/sina")
public class SinaReplyController {
    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public void test(String code){
        System.out.println(">>>>>>>>>>>>>>>:"+code);
    }
}
