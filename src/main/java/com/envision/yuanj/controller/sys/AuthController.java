package com.envision.yuanj.controller.sys;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {

    @RequestMapping("login")
    public String index(){
        return "login";
    }

    @RequestMapping("signIn")
    public String signIn(){
        Session session = SecurityUtils.getSubject().getSession();
        if (session == null || session.getAttribute("currentUser") == null) {
            return "/login";
        } else {
            return "redirect:/home";
        }
    }
}
