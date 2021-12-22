package com.szeastroc.middle.code.syn.config.db.redis;

/**
 * @Description:
 * @Author: futao
 * @Date: 2021/12/14
 **/
public class RedisPrefix {
    /**
     * 统一工程前缀
     */
    public static final String COMMON="MIDDLE_CODE:";
    /**
     * 当前登录用户
     */
    public static final String CURRENT_USER=COMMON+"LOGIN_COUNT:";

    public static final String TOKEN_USERID = "token_userid";
    public static final String TOKEN_LOGIN_TIME = "token_login_time";

}
