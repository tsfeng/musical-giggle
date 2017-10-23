package com.tsfeng.cn.service.impl;

import com.tsfeng.cn.entity.User;
import com.tsfeng.cn.repository.UserRepositoty;
import com.tsfeng.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 18:44
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepositoty userRepositoty;

    @Override
    public User findByUserId(Long id) {
        return userRepositoty.findByUserId(id);
    }
}
