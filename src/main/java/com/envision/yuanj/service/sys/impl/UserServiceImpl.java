package com.envision.yuanj.service.sys.impl;

import com.envision.yuanj.dao.UserDao;
import com.envision.yuanj.entity.User;
import com.envision.yuanj.service.sys.UserService;
import com.envision.yuanj.web.socket.SocketPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User authticationUser(String userName,String passWord) {
        Optional<User> optional = userDao.findTopByUserNameAndPassWord(userName, passWord);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public Set<String> authtizationRole(String username) {
        return null;
    }

    @Override
    public Set<String> authtizationPermission(String username) {
        return null;
    }

    @Override
    public int userAdd(User user) {
        User user1=userDao.save(user);
        if (user1!=null) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List userQuery() {
//        List list=userDao.findAll();
        Map map= SocketPoint.map;
        Set set=map.keySet();
        List list=new ArrayList<String>(set);
        if (list!=null) {
            return list;
        }else {
            return null;
        }
    }

}
