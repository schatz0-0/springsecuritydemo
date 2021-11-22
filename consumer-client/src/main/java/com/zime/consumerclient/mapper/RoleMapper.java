package com.zime.consumerclient.mapper;

import com.zime.consumerclient.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper
{
    List<Role>getRoleByUserName(String username);
    List<Role>getRoleById(long userId);
    void addRole(long roleId,String roleName);
    void deleteRole(long roleId);
    void updateRole(Role role);
    List<Role>getRoleList();
}
