package com.zime.consumerclient.service.impl;

import com.zime.consumerclient.util.SnowflakeIdWorker;
import com.zime.consumerclient.mapper.RoleMapper;
import com.zime.consumerclient.pojo.Role;
import com.zime.consumerclient.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> getRoleById(long id)
    {
        return roleMapper.getRoleById(id);
    }

    @Override
    public void deleteRole(long roleId)
    {
        roleMapper.deleteRole(roleId);
    }

    @Override
    public void addRole(String roleName)
    {
        roleMapper.addRole(new SnowflakeIdWorker(16L, 11L).nextId(), roleName);
    }

    @Override
    public void updateRole(Role role)
    {
        roleMapper.updateRole(role);
    }

    @Override
    public List<Role> getRoleList()
    {
        return roleMapper.getRoleList();
    }


}
