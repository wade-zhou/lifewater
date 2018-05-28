package com.wade.lifewater.service;

import com.wade.lifewater.model.User;

import java.util.Map;

public interface UserService {
    User selectUserByNameAndPassword(Map map);
}
