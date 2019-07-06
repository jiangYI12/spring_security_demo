package com.springsecurity.spring_security_demo.Entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private Integer id;

    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}