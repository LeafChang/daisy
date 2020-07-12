package com.gs.leaf.mode.strategyMode;

import org.springframework.stereotype.Service;

@Service
public class MayDayGiftHandler implements GiftInfoService {

    @Override
    public GiftInfo getGiftInfo(String activityId) {
        GiftInfo giftInfo = new GiftInfo("劳动节大礼包",288.00D);
        //根据活动号从数据库差礼物，略
        return giftInfo;
    }

    @Override
    public String getActivityId() {
        return "0501";
    }
}
