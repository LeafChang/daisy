package com.gs.leaf.mode.strategyMode;

/**
 * 获取礼物服务
 * */
public interface GiftInfoService {

    GiftInfo getGiftInfo(String activityId);

    String getActivityId();

}
