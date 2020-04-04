package com.envision.yuanj.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.envision.yuanj.entity.User;
import com.envision.yuanj.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/add")
    public String addUser(User user){

        JSONObject returnJson=new JSONObject();
        if(userService.userAdd(user)==1){
            returnJson.put("msg","添加用户成功！");
            returnJson.put("code",1);
            returnJson.put("data",new JSONObject());
        }else{
            returnJson.put("msg","添加用户失败！");
            returnJson.put("code",0);
            returnJson.put("data",new JSONObject());
        }
        return returnJson.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/query")
    public String queryUsers(){
        JSONObject returnJson=new JSONObject();
        List list=userService.userQuery();
        if(list!=null){
            returnJson.put("msg","查询用户成功！");
            returnJson.put("code",1);
            returnJson.put("data",list);
        }else{
            returnJson.put("msg","查询用户失败！");
            returnJson.put("code",0);
            returnJson.put("data",new JSONObject());
        }
        return returnJson.toJSONString();
    }
}
