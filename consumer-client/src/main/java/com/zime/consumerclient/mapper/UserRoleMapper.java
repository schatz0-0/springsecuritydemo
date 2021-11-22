package com.zime.consumerclient.mapper;

import com.zime.consumerclient.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper
{
    List<UserRole>getUserRoleList();
    List<UserRole>getUserRole(UserRole userRole);
    void addUserRole(long userRoleId,long userId,long roleId);
    void updateUserRole(UserRole userRole);
    void deleteUserRole(long userRoleId);

}
