package com.tsfeng.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/23 14:43
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "welcome";
    }
}
