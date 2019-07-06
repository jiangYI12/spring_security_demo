package com.springsecurity.spring_security_demo.Controller;

import com.springsecurity.spring_security_demo.Entity.Users;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    public Authentication info(Authentication authentication){
        return authentication;
    }
}
