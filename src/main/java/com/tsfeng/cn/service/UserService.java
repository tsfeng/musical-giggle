package com.tsfeng.cn.service;

import com.tsfeng.cn.entity.User;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 12:12
 */
public interface UserService {

    /**
     * 根据name查找User
     * @param name 名称
     * @return User
     */
    User findByUserName(String name);
}
