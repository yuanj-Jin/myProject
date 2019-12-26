package com.envision.yuanj.controller.sys;

import com.envision.yuanj.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @RequestMapping("goLogin")
    public String index(){
        Session session = SecurityUtils.getSubject().getSession();
        if (session == null || session.getAttribute("currentUser") == null) {
            return "/login";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("signIn")
    public String login(@RequestParam String userName,@RequestParam String passWord,Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("userName", "用户名错误！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("passwd", "密码错误");
            return "login";
        }
        return "home";
    }


}
