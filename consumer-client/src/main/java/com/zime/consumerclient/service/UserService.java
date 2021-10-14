package com.zime.consumerclient.service;

import com.zime.consumerclient.mode.Result;
import com.zime.consumerclient.mode.User;

import java.util.List;

public interface UserService {

    Result getUserById(String id);
    Result deleteUserById(String id);
    Result<List> getUserAll();
    List<User> getUserList();
    List<User> getUser(User user);
    User getUserByUsername(String username);
    User getUserByName(String username);
    User getUserById(long userid);
    Result addUser(User user);
    Result updateUser(User user);
    void deleteUser(long userId);
}
