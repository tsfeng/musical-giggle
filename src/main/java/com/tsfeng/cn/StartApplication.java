package com.tsfeng.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/23 16:06
 */
@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    /**
     * 把默认的Tomcat容器改为了Jetty容器，同时修改pom.xml,把默认的Tomcat容器去除，然后引入Jetty依赖
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        return new JettyEmbeddedServletContainerFactory();
    }
}
