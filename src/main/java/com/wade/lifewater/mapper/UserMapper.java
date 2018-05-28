package com.wade.lifewater.mapper;

import com.wade.lifewater.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {
    User selectUserByNameAndPassword(Map map);
}
