package com.envision.yuanj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class VueTestController {

    @RequestMapping("/")
    public String goVueIndex(){
        return "index";
    }

    @RequestMapping("myApp")
    public String goSpringIndex(){
        return "indexMyApp";
    }
}
