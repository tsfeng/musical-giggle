package com.tsfeng.cn.service.impl;

import com.tsfeng.cn.entity.User;
import com.tsfeng.cn.repository.UserRepositoty;
import com.tsfeng.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepositoty.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepositoty.findAll();
    }

    @Override
    public void deleteByUserId(Long id) {
        userRepositoty.delete(id);
    }
}
