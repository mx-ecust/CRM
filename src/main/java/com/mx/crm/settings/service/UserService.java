package com.mx.crm.settings.service;

import com.mx.crm.exception.LoginException;
import com.mx.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
