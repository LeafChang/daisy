package com.gs.leaf.mode.strategyMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 礼物实体类
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftInfo {

    /** 礼物名称 */
    private String name;
    /** 礼物价值 */
    private Double price;


}
