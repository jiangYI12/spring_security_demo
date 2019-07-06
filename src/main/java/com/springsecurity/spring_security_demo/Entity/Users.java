package com.springsecurity.spring_security_demo.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class Users implements UserDetails{

    private String id;

    private String userName;

    private String account;

    private String password;

    private String phone;

    @TableField(exist=false)
    private List<Role> roles;

    public Users(){

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(!roles.isEmpty()){
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            for(Role role:roles){
                list.add(role);
            }
            return list;
        }
        return null;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

//账户是否过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

   //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

//指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//是否被禁用,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }
}