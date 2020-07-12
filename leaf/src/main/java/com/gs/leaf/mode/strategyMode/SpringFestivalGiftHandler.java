package com.gs.leaf.mode.strategyMode;

import org.springframework.stereotype.Service;

@Service
public class SpringFestivalGiftHandler implements GiftInfoService {

    @Override
    public GiftInfo getGiftInfo(String activityId) {
        GiftInfo giftInfo = new GiftInfo("春节大礼包",1288.00D);
        //根据活动号从数据库差礼物，略
        return giftInfo;
    }

    @Override
    public String getActivityId() {
        return "0101";
    }
}
