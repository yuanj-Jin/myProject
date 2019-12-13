package com.envision.yuanj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

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
}
