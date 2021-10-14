package com.zime.consumerclient.mapper;

import com.zime.consumerclient.mode.Result;
import com.zime.consumerclient.mode.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper{

    User getUserById(String id);
    int deleteUserById(String id);
    List<User> getAllUsers();
    List<User> getUserList();
    List<User> getUser(User user);
    User getUserByUsername(String username);
    User getUserByName(String username);
    User getUserById(long userid);
    int addUser(User user);
    int updateUser(User user);
    void deleteUser(long userId);
}
