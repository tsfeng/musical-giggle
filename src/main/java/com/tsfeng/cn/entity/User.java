package com.tsfeng.cn.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 09:45
 */
@Data
@Accessors(chain = true)
@Table(name = "user")
public class User {

    private Long id;

    private String name;

}
