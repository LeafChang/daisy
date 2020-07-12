package com.gs.leaf.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gs.leaf.constant.RedisConstant;
import com.gs.leaf.mapper.UserMapper;
import com.gs.leaf.pojo.User;
import com.gs.leaf.service.UserService;
import com.gs.leaf.util.redis.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    //private UserMapper userMapper;

    //@Autowired
    //private RedisOperator redis;

    @Override
    public User getUserById(Integer id) {
      /*  String redisKey = RedisConstant.getUserRedisKey(String.valueOf(id));
        User user = null;
        if (redis.exists(redisKey)){
            user = JSONObject.parseObject(redis.get(redisKey),User.class);
        }
        user = userMapper.selectByPrimaryKey(id);
        if (user != null){
            redis.set(redisKey,JSONObject.toJSONString(user), RedisConstant.CACHE_ONE_DAY);
        }*/
        return new User();
    }
}
