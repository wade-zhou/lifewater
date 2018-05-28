package com.wade.lifewater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目默认访问界面
 */
@Controller
public class DefaultView {

    @RequestMapping("/")
    public String defaultView(){
        return "login";

    }
}
