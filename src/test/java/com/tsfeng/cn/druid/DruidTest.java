package com.tsfeng.cn.druid;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * druid加解密
 * @author tsfeng
 * @version 创建时间 2017/10/27 15:21
 */
public class DruidTest {
    public static void main(String[] args) throws Exception {
        //密码明文
        String password = "root";
        System.out.println("密码[ " + password + " ]的加密信息如下：");
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];
        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);
        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);
        System.out.println("password:" + password);
        System.out.println("解密如下：");
        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("decryptPassword：" + decryptPassword);
    }
}
