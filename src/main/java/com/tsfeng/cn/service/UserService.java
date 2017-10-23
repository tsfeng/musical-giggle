package com.tsfeng.cn.service;

import com.tsfeng.cn.entity.User;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 12:12
 */
public interface UserService {

    /**
     * 根据name查找User
     * @param id id
     * @return User
     */
    User findByUserId(Long id);
}
