package com.zime.consumerclient.service.impl;

import com.zime.consumerclient.util.SnowflakeIdWorker;
import com.zime.consumerclient.mapper.UserRoleMapper;
import com.zime.consumerclient.mode.UserRole;
import com.zime.consumerclient.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService
{
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> getUserRoleList()
    {
        return userRoleMapper.getUserRoleList();
    }

    @Override
    public List<UserRole> getUserRole(UserRole userRole)
    {
        return userRoleMapper.getUserRole(userRole);
    }

    @Override
    public void addUserRole(long userId, long roleId)
    {
        userRoleMapper.addUserRole(new SnowflakeIdWorker(8L, 7L).nextId(),userId,roleId);
    }

    @Override
    public void updateUserRole(UserRole userRole)
    {
        userRoleMapper.updateUserRole(userRole);
    }

    @Override
    public void deleteUserRole(long userRoleId)
    {
        userRoleMapper.deleteUserRole(userRoleId);
    }
}
