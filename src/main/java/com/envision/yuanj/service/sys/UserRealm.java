package com.envision.yuanj.service.sys;

import com.envision.yuanj.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
        String userName =(String) principal.getPrimaryPrincipal();
        //从数据库中获取角色数据
        Set<String> role=userService.authtizationRole(userName);
        Set<String> permission=userService.authtizationPermission(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(role);
        simpleAuthorizationInfo.setStringPermissions(permission);
        return simpleAuthorizationInfo;
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
        //去数据库认证
        User authUser = userService.authticationUser(userToken.getUsername(),passWord);
        if (authUser != null) {
            return new SimpleAuthenticationInfo(authUser, userToken.getCredentials(), userToken.getUsername());
        } else {
            return null;
        }
    }
}
