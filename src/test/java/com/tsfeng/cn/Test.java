package com.tsfeng.cn;

import java.util.Date;

/**
 * @author tsfeng
 * @version 创建时间 2017/11/3 16:17
 */
public class Test {
    public static void main(String[] args) {
        int i = 1, j = 1;
        int n = 0 - i;
        int m = -j;
        Date d = new Date();
        Date d1 = new java.sql.Date(d.getTime());
        System.out.println(d.getTime());
        System.out.println(d1.getTime());
    }
}
