package com.envision.yuanj.service.sys.impl;

import com.envision.yuanj.dao.UserDao;
import com.envision.yuanj.entity.User;
import com.envision.yuanj.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
