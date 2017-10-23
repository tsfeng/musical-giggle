package com.tsfeng.cn.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 09:45
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User {

    @Id
    private Long id;

    private String name;

}
