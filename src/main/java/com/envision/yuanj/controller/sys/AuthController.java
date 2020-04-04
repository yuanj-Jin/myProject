package com.envision.yuanj.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.envision.yuanj.entity.User;
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
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping("signIn")
    public String login(@RequestParam String userName,@RequestParam String passWord) {
        JSONObject returnJson=new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        try {
            subject.login(token);
            User user=(User)subject.getPrincipal();
            returnJson.put("username",user);
            returnJson.put("auth","auth");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            returnJson.put("msg","用户名或密码错误！");
            returnJson.put("code",1);
            returnJson.put("data",new JSONObject());
        } catch (IncorrectCredentialsException e) {
            returnJson.put("msg","密码错误！");
            returnJson.put("code",1);
            returnJson.put("data",new JSONObject());
        }
        return returnJson.toJSONString();
    }


}
