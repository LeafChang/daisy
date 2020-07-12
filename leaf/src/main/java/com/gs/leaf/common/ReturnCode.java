package com.gs.leaf.common;


public enum ReturnCode {

    SUCCESS("00", "访问成功"),
    FAIL("09", "访问失败");

    private String code;
    private String msg;

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


