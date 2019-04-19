package com.tsfeng.cn.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/31 14:58
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "city")
public class City implements Serializable{

    private static final long serialVersionUID = -3146776361011946187L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String code;

    private Date createDate;
}
