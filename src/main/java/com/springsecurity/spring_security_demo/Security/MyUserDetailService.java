package com.springsecurity.spring_security_demo.Security;

import com.springsecurity.spring_security_demo.Dao.RoleMapper;
import com.springsecurity.spring_security_demo.Dao.UsersMapper;
import com.springsecurity.spring_security_demo.Entity.Role;
import com.springsecurity.spring_security_demo.Entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RoleMapper roleMapper;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = new Users();
        if(userName.indexOf("phones")>0){
            users.setPhone(userName.replace("phones",""));
        }else {
            users.setAccount(userName);
        }
        Users user = usersMapper.selectByPrimaryKeySelective(users);
        logger.info("用户账号{} 权限{}",user.getAccount());
        user.getRoles();
        return user;
    }
}
