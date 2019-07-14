package com.springsecurity.spring_security_demo.Controller;

import com.springsecurity.spring_security_demo.Entity.Users;
import com.springsecurity.spring_security_demo.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class Register {
    @Autowired
    RegisterService registerService;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @RequestMapping("/register")
    @ResponseBody
    public void register(Users users,HttpServletRequest request){
        //第三方登陆用户注册
         providerSignInUtils.doPostSignUp(users.getUsername(),new ServletWebRequest(request));
    }

    @RequestMapping("/registerpage")
    public ModelAndView registerPage(ModelAndView modelAndView){
        return new ModelAndView("signUp");
    }
    @RequestMapping("/info")
    @ResponseBody
    public Connection<?> userInfo(HttpServletRequest request){
        //获取第三方登录用户信息
        return providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
    }
    //绑定页面
    @RequestMapping("/binding")
    public ModelAndView binding(ModelAndView modelAndView){
        return new ModelAndView("binding");
    }

}
