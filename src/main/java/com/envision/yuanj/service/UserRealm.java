package com.envision.yuanj.service;

import com.envision.yuanj.entity.User;
import com.envision.yuanj.service.impl.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Object obj = principal.getPrimaryPrincipal();
        System.out.println(obj);
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User authUser = userService.authticationUser(userToken.getUsername());
        if (authUser != null) {
            return new SimpleAuthenticationInfo(authUser, "", "");
        } else {
            return null;
        }
    }
}
