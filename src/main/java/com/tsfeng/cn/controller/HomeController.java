package com.tsfeng.cn.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/23 14:43
 */
@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");
        return "welcome";
    }
}
