package com.tsfeng.cn.controller;

import com.tsfeng.cn.entity.User;
import com.tsfeng.cn.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/23 14:11
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        map.addAttribute("name", "World ");
        return "hello";
    }

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public User findByUserId(@PathVariable Long id) {
        User user = null;
        try {
            user = userService.findByUserId(id);
            logger.info(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
