package com.springsecurity.spring_security_demo.Dao;

import com.springsecurity.spring_security_demo.Entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper  {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}