package com.gs.leaf.mapper;

import com.gs.leaf.pojo.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface UserMapper {

    User selectByPrimaryKey(Integer id);
}
