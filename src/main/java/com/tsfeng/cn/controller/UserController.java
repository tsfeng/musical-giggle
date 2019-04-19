package com.tsfeng.cn.controller;

import com.tsfeng.cn.entity.User;
import com.tsfeng.cn.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        map.addAttribute("name", "欢迎使用Thymeleaf");
        logger.info("hello world!");
        return "hello";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findByUserId(@PathVariable Long id) {
        logger.info("[id=" + id + "]");
        User user = null;
        try {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            if (redisTemplate.hasKey(id)) {
                logger.info(valueOperations.get(id));
            } else {
                user = userService.findByUserId(id);
                logger.info(user);
                valueOperations.set(id, user);
                logger.info("redis update ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
