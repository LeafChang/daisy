package com.gs.leaf.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


public class ResponseUtil {

    private static final String SYSTEM_CODE = "00";

    private String code;
    private String data;
    private String msg;

    public static Object successResponse(){
        return successResponse(null);
    }

    public static Object successResponse(Object data){
        Map<String,Object> resMap = new HashMap<String, Object>();
        resMap.put("CODE",ReturnCode.SUCCESS.getCode());
        if (data != null){
            resMap.put("DATA",data);
        }
        resMap.put("MSG","访问成功");
        return resMap;
    }

    public static Object failResponse(ReturnCode returnCode,ReturnModule module){
        return failResponse(returnCode,module,"");
    }

    public static Object failResponse(ReturnCode returnCode,ReturnModule module,String msg){
        Map<String,Object> resMap = new HashMap<String, Object>();
        if (returnCode == null){
            returnCode = ReturnCode.FAIL;
        }
        if (module == null){
            module = ReturnModule.MODULE_COMMON;
        }
        StringBuilder code = new StringBuilder(SYSTEM_CODE);
        code.append(module.getModelCode()).append(returnCode.getCode());
        resMap.put("CODE",code.toString());
        if (StringUtils.isEmpty(msg)){
            msg = ReturnCode.FAIL.getMsg();
        }
        resMap.put("MSG",msg);
        return resMap;
    }


    public ResponseUtil(String code, String data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
