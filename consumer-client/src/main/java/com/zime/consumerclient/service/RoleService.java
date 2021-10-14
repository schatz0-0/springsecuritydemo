package com.zime.consumerclient.service;

import com.zime.consumerclient.mode.Role;

import java.util.List;

public interface RoleService
{

    List<Role> getRoleById(long id);
    void addRole(String roleName);
    void deleteRole(long roleId);
    void updateRole(Role role);
    List<Role>getRoleList();
}
