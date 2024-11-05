package com.iflove.starter.common.constant;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class RedisKey {
    private static final String BASE_KEY = "IPS:";

    // 登录令牌过期时间 ( 7小时 )
    public static final int TOKEN_EXPIRE_TIME = 7;
    // Token 白名单
    public static final String JWT_BLACK_LIST = "jwt:blacklist:%s";
    // Token 黑名单
    public static final String JWT_WHITE_LIST = "jwt:whitelist:%s";

    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }
}
