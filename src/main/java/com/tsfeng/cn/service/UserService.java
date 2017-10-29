package com.tsfeng.cn.service;

import com.tsfeng.cn.entity.User;

import java.util.List;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 12:12
 */
public interface UserService {

    /**
     * 根据id查找User
     * @param id id
     * @return User
     */
    User findByUserId(Long id);

    /**
     *  查找所有User
     * @return userList
     */
    List<User> findAll();

    /**
     * 根据id删除User
     * @param id
     */
    void deleteByUserId(Long id);

}
