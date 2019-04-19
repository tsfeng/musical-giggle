package com.tsfeng.cn;

import com.tsfeng.cn.util.RegexUtil;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/18 15:41
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("helloWorld");

        System.out.println("isEmail:" + RegexUtil.isEmail("1@1.1c"));
        System.out.println("isUrl:" + RegexUtil.isUrl("https://b"));
        System.out.println("isIpAddress:" + RegexUtil.isIpAddress("192.111.1.12"));
        System.out.println("isChinese:" + RegexUtil.isChinese("中"));
        System.out.println("isLegalAccount:" + RegexUtil.isLegalAccount("aaaaabbbbbcccccde"));
    }
}
