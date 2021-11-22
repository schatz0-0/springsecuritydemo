package com.zime.consumerclient.service.impl;

import com.zime.consumerclient.mapper.UserMapper;
import com.zime.consumerclient.pojo.AffectedRows;
import com.zime.consumerclient.pojo.User;
import com.zime.consumerclient.service.UserService;
import com.zime.consumerclient.vo.ResultGenerator;
import com.zime.consumerclient.vo.ResultVo;
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
    public ResultVo addUser(User user) {
        int result = userMapper.addUser(user);
        if (result == 1){
            return ResultGenerator.genSuccessResult(new AffectedRows(result), "添加成功");
        }
        else
        {
            return ResultGenerator.genFailResult("添加失败");
        }

    }

    @Override
    public ResultVo getUserById(String id) {
        User user = userMapper.getUserById(id);
        if (user!=null){
            return ResultGenerator.genSuccessResult(user, "查询成功");
        }
        else
        {
            return ResultGenerator.genFailResult("查询失败请检查身份证号码");
        }


    }

    @Override
    public ResultVo deleteUserById(String id) {

        int result1 = userMapper.deleteUserById(id);
        if (result1 == 1){
            return ResultGenerator.genSuccessResult(new AffectedRows(result1), "删除成功");
        }
        else
        {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    @Override
    public ResultVo updateUser(User user) {
        System.out.println(user);
        int result = userMapper.updateUser(user);
        if (result == 1){
            return ResultGenerator.genSuccessResult(new AffectedRows(result), "添加成功");
        }
        else
        {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public ResultVo getUserAll() {
        List<User> list = userMapper.getAllUsers();
        if (!list.isEmpty()){
            return ResultGenerator.genSuccessResult(list, "查询成功");
        }
        else
        {
            return ResultGenerator.genFailResult("查询失败");
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
