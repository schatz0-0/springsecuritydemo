package com.zime.consumerclient.mapper;

import com.zime.consumerclient.mode.JwtUser;
import com.zime.consumerclient.mode.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JwtUserMapper {


    JwtUser loadUserByUsername(String username);
    List<Role> getJwtUserRolesByUid(Integer id);
}
