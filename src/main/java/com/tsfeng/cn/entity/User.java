package com.tsfeng.cn.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 09:45
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer age;

}
