package com.gs.leaf.controller;

import com.gs.leaf.common.ResponseUtil;
import com.gs.leaf.common.ReturnCode;
import com.gs.leaf.common.ReturnModule;
import com.gs.leaf.constant.RedisConstant;
import com.gs.leaf.mode.strategyMode.GiftInfoContext;
import com.gs.leaf.mode.strategyMode.GiftInfoService;
import com.gs.leaf.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "用户相关Api")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //@Autowired
    //private RedisOperator redis;

    @Autowired
    GiftInfoContext context;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @RequestMapping("get")
    public Object getUser(@RequestParam(value = "id",required = false) String id){
        //User user =userService.getUserById(Integer.valueOf(id));
        return ResponseUtil.successResponse();
    }


    @ApiIgnore
    @RequestMapping("getFromRedis")
    public Object getFromRedis(@RequestParam(value = "id",defaultValue = "1") String id){
       /* String userRedisKey = RedisConstant.getUserRedisKey(id);
        RedisScript<List> redisScript = new RedisScript<List>() {
            @Override
            public String getSha1() {
                return null;
            }

            @Override
            public Class<List> getResultType() {
                return List.class;
            }

            @Override
            public String getScriptAsString() {
                String luaString = " local retVal;" +
                                   " retVal = redis.call('get',KEYS[0]); " +
                                   " return retVal; ";
                return luaString;
            }
        };*/
        //Object userStr =  redis.executeLuaScript(redisScript, Collections.singletonList(userRedisKey),Collections.singletonList("100"));
        //System.out.println("userStr : " + JSONObject.toJSONString(userStr));
        //User user = JSONObject.parseObject(userStr,User.class);
        return ResponseUtil.successResponse();
    }



    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @RequestMapping("/get/test")
    public Object testStrategy(@RequestParam(value = "activityId") String activityId){
        GiftInfoService service = context.getService(activityId);
        if (service == null){
            return ResponseUtil.failResponse(ReturnCode.FAIL, ReturnModule.MODULE_COMMON);
        }
        return ResponseUtil.successResponse(service.getGiftInfo(activityId));
    }

}
