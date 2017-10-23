package com.tsfeng.cn.controller;

import com.tsfeng.cn.entity.User;
import com.tsfeng.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/23 14:11
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public User findByUserId(@PathVariable Long id) {
        User user = null;
        try {
            user = userService.findByUserId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
