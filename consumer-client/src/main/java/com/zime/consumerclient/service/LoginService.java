package com.zime.consumerclient.service;

import com.zime.consumerclient.mode.User;

public interface LoginService {
    User listUser(Long id);
    User getUserByUsername(String username);
}
