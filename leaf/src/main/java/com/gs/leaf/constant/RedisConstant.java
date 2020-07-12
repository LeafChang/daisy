package com.gs.leaf.constant;

import java.text.MessageFormat;

public class RedisConstant {

    public static final long CACHE_ONE_HOUR = 3600;
    public static final long CACHE_ONE_DAY = 86400;
    public static final long CACHE_ONE_WEEK = 7 * CACHE_ONE_DAY;
    public static final long CACHE_ONE_MONTH = 30 * CACHE_ONE_DAY;

    public static String getUserRedisKey(String userId){
        return MessageFormat.format("s:user:obj:{0}",userId);
    }



}
