package com.springsecurity.spring_security_demo.Dao;

import com.springsecurity.spring_security_demo.Entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}