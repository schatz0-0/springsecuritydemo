package com.zime.consumerclient.service;

import com.zime.consumerclient.pojo.User;
import com.zime.consumerclient.vo.ResultVo;

import java.util.List;

public interface UserService {

    ResultVo getUserById(String id);
    ResultVo deleteUserById(String id);
    ResultVo<List> getUserAll();
    List<User> getUserList();
    List<User> getUser(User user);
    User getUserByUsername(String username);
    User getUserByName(String username);
    User getUserById(long userid);
    ResultVo addUser(User user);
    ResultVo updateUser(User user);
    void deleteUser(long userId);
}
