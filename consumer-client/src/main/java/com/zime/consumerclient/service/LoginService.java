package com.zime.consumerclient.service;

import com.zime.consumerclient.pojo.User;

public interface LoginService {
    User listUser(Long id);
    User getUserByUsername(String username);
}
