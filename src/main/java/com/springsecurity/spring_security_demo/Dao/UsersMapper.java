package com.springsecurity.spring_security_demo.Dao;

import com.springsecurity.spring_security_demo.Entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByPrimaryKeySelective(Users users);
}