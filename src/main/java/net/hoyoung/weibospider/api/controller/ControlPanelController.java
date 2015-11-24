package net.hoyoung.weibospider.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/11/24.
 */
@Controller
@RequestMapping("control")
public class ControlPanelController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(){

        return new ModelAndView("spider/control_panel");
    }
}
