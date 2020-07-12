package com.gs.leaf.mode.strategyMode;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/** 礼物环境类 */
@Component
public class GiftInfoContext {

    @Resource
    List<GiftInfoService> serviceList;


    private volatile Map<String,GiftInfoService> serviceMap;

    public GiftInfoService getService(String activityId){
        if (serviceMap == null){
            synchronized (this){
                if (serviceMap == null){
                    serviceMap = Maps.newHashMap();
                    serviceList.stream().forEach(service->serviceMap.put(service.getActivityId(),service));
                }
            }
        }
        return serviceMap.get(activityId);
    }



}
