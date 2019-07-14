package com.springsecurity.spring_security_demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class Index {
    @RequestMapping("/welcome")
    public String welcome(){
        return "hello welcome";
    }

    @RequestMapping("/welcomeTwo")
    public String welcomeTwo(){
        return "hello welcome Two";
    }

    @RequestMapping("/successlogin")
    public String successlogin(){
        return "登录成功跳转页面";
    }

    @RequestMapping("/failerlogin")
    public String failerlogin(){
        return "登录失败跳转页面";
    }

    @RequestMapping("/qqLogin")
    public String qqLogin(){
        return "qqLogin ";
    }

}
