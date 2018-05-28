package com.wade.lifewater.service.impl;

import com.wade.lifewater.mapper.UserMapper;
import com.wade.lifewater.model.User;
import com.wade.lifewater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectUserByNameAndPassword(Map map) {
        return userMapper.selectUserByNameAndPassword(map);
    }
}
