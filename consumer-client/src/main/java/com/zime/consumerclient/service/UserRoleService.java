package com.zime.consumerclient.service;

import com.zime.consumerclient.pojo.UserRole;

import java.util.List;

public interface UserRoleService
{
    List<UserRole> getUserRoleList();
    List<UserRole>getUserRole(UserRole userRole);
    void addUserRole(long userId,long roleId);
    void updateUserRole(UserRole userRole);
    void deleteUserRole(long userRoleId);
}
