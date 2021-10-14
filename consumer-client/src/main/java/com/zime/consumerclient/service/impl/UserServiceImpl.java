package com.zime.consumerclient.service.impl;

import com.zime.consumerclient.mapper.UserMapper;
import com.zime.consumerclient.mode.AffectedRows;
import com.zime.consumerclient.mode.Result;
import com.zime.consumerclient.mode.ReturnCode;
import com.zime.consumerclient.mode.User;
import com.zime.consumerclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public Result addUser(User user) {
        int result = userMapper.addUser(user);
        if (result == 1){
            return new Result<AffectedRows>(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getType(),
                    "添加成功",new AffectedRows(result)
            );
        }
        else
        {
            return new Result<AffectedRows>(
                    ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getType(),
                    "添加失败",new AffectedRows(result)
            );
        }

    }

    @Override
    public Result getUserById(String id) {
        User user = userMapper.getUserById(id);
        if (user!=null){
            return new Result<User>(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getType(),
                    "查询成功",user
            );
        }
        else
        {
            return new Result<String>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getType(),
                    "查询失败请检查身份证号码",null
            );
        }


    }

    @Override
    public Result deleteUserById(String id) {

        int result1 = userMapper.deleteUserById(id);
        if (result1 == 1){
            return new Result<AffectedRows>(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getType(),
                    "删除成功",new AffectedRows(result1)
            );
        }
        else
        {
            return new Result<AffectedRows>(
                    ReturnCode.ERROR.getCode(), ReturnCode.ERROR.getType(),
                    "删除失败",new AffectedRows(result1)
            );
        }
    }

    @Override
    public Result updateUser(User user) {
        System.out.println(user);
        int result = userMapper.updateUser(user);
        if (result == 1){
            return new Result<AffectedRows>(
                    ReturnCode.SUCCESS.getCode(),
                    ReturnCode.SUCCESS.getType(),
                    "添加成功",new AffectedRows(result)
            );
        }
        else
        {
            return new Result<AffectedRows>(
                    ReturnCode.ERROR.getCode(), ReturnCode.ERROR.getType(),
                    "添加失败",new AffectedRows(result)
            );
        }
    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public Result getUserAll() {
        List<User> list = userMapper.getAllUsers();
        if (!list.isEmpty()){
            return new Result<List>(
                    ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getType(), "查询成功",list
            );
        }
        else
        {
            return new Result<String>(
                    ReturnCode.ERROR.getCode(),
                    ReturnCode.ERROR.getType(),
                    "查询失败",null
            );
        }
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public List<User> getUser(User user) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public User getUserById(long userid) {
        return null;
    }
}
