package com.envision.yuanj.service.impl;

import com.envision.yuanj.dao.UserDao;
import com.envision.yuanj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public User authticationUser(String userName) {
        Optional<User> optional = userDao.findTopByUserNameAndStatus(userName, 1);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
        return null;
    }
}
