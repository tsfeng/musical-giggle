package com.tsfeng.cn.util;

import com.tsfeng.cn.constant.RegexConsts;

import java.util.regex.Pattern;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/20 14:22
 */
public class RegexUtil {

    /**
     * 网址（URL）
     */
    public static boolean isUrl(String input) {
        return isMatch(RegexConsts.URL_REGEX, input);
    }

    /**
     * IP地址(IP Address)
     */
    public static boolean isIpAddress(String input) {
        return isMatch(RegexConsts.IP_ADDRESS_REGEX, input);
    }

    /**
     * 电子邮件(Email)
     */
    public static boolean isEmail(String input) {
        return isMatch(RegexConsts.EMAIL_REGEX, input);
    }

    /**
     * 汉字(字符)
     */
    public static boolean isChinese(String input) {
        return isMatch(RegexConsts.CHINESE_REGEX, input);
    }

    /**
     * 合法的名字（字母开头，允许5-16字节，允许字母数字下划线）
     */
    public static boolean isLegalAccount(String input) {
        return isMatch(RegexConsts.LEGAL_ACCOUNT_REGEX, input);
    }


    public static boolean isMatch(String regex, String input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }
}
