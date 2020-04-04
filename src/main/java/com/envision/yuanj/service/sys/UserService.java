package com.envision.yuanj.service.sys;

import com.envision.yuanj.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User authticationUser(String username,String passWord);

    Set<String> authtizationRole(String username);
    Set<String> authtizationPermission(String username);

    int userAdd(User user);

    List userQuery();
}
