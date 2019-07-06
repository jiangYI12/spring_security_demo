package com.springsecurity.spring_security_demo.Security.Mobile;

import com.springsecurity.spring_security_demo.Security.AuthenctiationFailureHandler;
import com.springsecurity.spring_security_demo.Security.AuthenticationSuccessHandler;
import com.springsecurity.spring_security_demo.Security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsCodeAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
   @Autowired
   MyUserDetailService myUserDetailService;

   @Autowired
   AuthenctiationFailureHandler myFailureHandler;

   @Autowired
    AuthenticationSuccessHandler mySuccessHandler;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(
                httpSecurity.getSharedObject(AuthenticationManager.class)
        );
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(mySuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myFailureHandler);
        //创建对应Provider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(myUserDetailService);
        //对应provider
        httpSecurity.authenticationProvider(smsCodeAuthenticationProvider)
                //添加在 UsernamePasswordAuthenticationFilter.class 过滤器之前
                .addFilterAfter(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);

    }
}
