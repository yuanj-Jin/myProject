package com.envision.yuanj;

import com.envision.yuanj.service.sys.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class YuanjApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuanjApplication.class, args);
    }

    //注意一定添加，springboot环境中使用的时embed tomcat，不具备完整的JavaEE功能
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> fMap = new HashMap<String, String>();
        // 拦截页面
        fMap.put("/**", "authc");

        // 开发阶段全部放行
         fMap.put("/**", "anon");
        fMap.put("/login/**", "anon");
        fMap.put("/elk/agent/regist", "anon");
        fMap.put("/elk/cpu/remote", "anon");
        fMap.put("/elk/disk/remote", "anon");

        // 静态资源
        fMap.put("/static/**", "anon");

        fMap.put("/logout", "anon");
        // fMap.put("/login/unifyLogin", "anon");
        fMap.put("/loginpage", "anon");
        fMap.put("/loginerror", "anon");
        // fMap.put("*/open.e.189.cn/**", "anon");

        // 拦截未授权
        // fMap.put("/one", "perms[user:one]");
        // fMap.put("/two", "perms[user:two]");
        // 被拦截返回登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // shiroFilterFactoryBean.setSuccessUrl("/login");
        // 授权拦截返回页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/autherror");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fMap);
        return shiroFilterFactoryBean;

    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
}
