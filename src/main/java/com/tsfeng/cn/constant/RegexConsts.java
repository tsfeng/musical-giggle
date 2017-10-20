package com.tsfeng.cn.constant;

/**
 * 说明：正则表达式通常用于两种任务：1.验证，2.搜索/替换。
 * 用于验证时，通常需要在前后分别加上^和$，以匹配整个待验证字符串；
 * 搜索/替换时是否加上此限定则根据搜索的要求而定，此外，也有可能要在前后加上\b而不是^和$
 * @author tsfeng
 * @version 创建时间 2017/10/20 13:46
 */
public class RegexConsts {

    /**
     * 网址（URL）
     */
    public static final String URL_REGEX = "[a-zA-z]+://[^\\s]*";

    /**
     * IP地址(IP Address)
     */
    public static final String IP_ADDRESS_REGEX = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * 电子邮件(Email)
     */
    public static final String EMAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    /**
     * 汉字(字符)
     */
    public static final String CHINESE_REGEX = "[\\u4e00-\\u9fa5]";

}
