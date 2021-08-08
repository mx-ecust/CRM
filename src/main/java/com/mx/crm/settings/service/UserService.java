package com.mx.crm.settings.service;

import com.mx.crm.exception.LoginException;
import com.mx.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
