package com.mx.crm.settings.dao;

import com.mx.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String, String> map);
}
