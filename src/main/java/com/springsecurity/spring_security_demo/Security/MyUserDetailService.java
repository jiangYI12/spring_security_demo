package com.springsecurity.spring_security_demo.Security;

import com.springsecurity.spring_security_demo.Dao.RoleMapper;
import com.springsecurity.spring_security_demo.Dao.UsersMapper;
import com.springsecurity.spring_security_demo.Entity.Role;
import com.springsecurity.spring_security_demo.Entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//SocialUserDetailsService 社交登陆认证
@Component
public class MyUserDetailService implements UserDetailsService,SocialUserDetailsService {

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

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        Users users = new Users();
        users.setAccount(s);
        Users user = usersMapper.selectByPrimaryKeySelective(users);
        logger.info("用户账号{} 权限{}",user.getAccount());
        user.getRoles();
        return user;
    }
}
