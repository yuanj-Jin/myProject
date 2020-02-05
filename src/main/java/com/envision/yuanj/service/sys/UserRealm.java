package com.envision.yuanj.service.sys;

import com.envision.yuanj.entity.User;
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

    /**
     * 用户权限认证
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Object obj = principal.getPrimaryPrincipal();
        return null;
    }

    /**
     * 用户身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        char[] pwd=userToken.getPassword();
        String passWord=String.valueOf(pwd);
        User authUser = userService.authticationUser(userToken.getUsername(),passWord);
        if (authUser != null) {
            return new SimpleAuthenticationInfo(authUser, userToken.getCredentials(), userToken.getUsername());
        } else {
            return null;
        }
    }
}
