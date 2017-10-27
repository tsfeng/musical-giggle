//package com.tsfeng.cn.druid;
//
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * druid监控-注解实现；Spring-boot配置application.yml更简洁
// * @author tsfeng
// * @version 创建时间 2017/10/27 13:45
// */
//@Configuration
//public class DruidConfiguration {
//
//    @Bean
//    public ServletRegistrationBean druidStatViewServlet() {
//        //
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        //设置IP白名单
//        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
//        //设置IP黑名单 (存在共同时，deny优先于allow) :
//        servletRegistrationBean.addInitParameter("deny", "127.0.0.2");
//        //设置控制台用户密码
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "admin");
//        //是否能够重置数据.
//        servletRegistrationBean.addInitParameter("resetEnable","false");
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean druidStatFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//        //添加过滤规则.
//        filterRegistrationBean.addUrlPatterns("/*");
//        //添加不需要忽略的格式信息.
//        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//    }
//}
