package com.springsecurity.spring_security_demo.Service;

import com.springsecurity.spring_security_demo.Dao.UsersMapper;
import com.springsecurity.spring_security_demo.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegisterService {
    @Autowired
    UsersMapper usersMapper;
    private PasswordEncoder myPasswordEncoder;


    public void setMyPasswordEncoder(@Autowired  PasswordEncoder myPasswordEncoder) {
        this.myPasswordEncoder = myPasswordEncoder;
    }

    public void insertUser(Users users){
        users.setPassword(myPasswordEncoder.encode(users.getPassword()));
        usersMapper.insertSelective(users);
    }
}
