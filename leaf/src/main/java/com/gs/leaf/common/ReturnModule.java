package com.gs.leaf.common;

public enum ReturnModule {


    MODULE_USER("01", "用户模块"),
    MODULE_COMMON("00", "公共模块");


    private String modelCode;
    private String modelName;

    ReturnModule(String modelCode, String modelName) {
        this.modelCode = modelCode;
        this.modelName = modelName;
    }


    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    }
