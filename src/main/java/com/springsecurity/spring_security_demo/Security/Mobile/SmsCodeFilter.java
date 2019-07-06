package com.springsecurity.spring_security_demo.Security.Mobile;

//拦截校验

import com.springsecurity.spring_security_demo.Security.AuthenctiationFailureHandler;
import com.springsecurity.spring_security_demo.Util.RedisUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    AuthenctiationFailureHandler myFailureHandler;
    @Autowired
    RedisUtil redisUtil;
    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(httpServletRequest.getRequestURI().equals("/authentication/mobile")){
            String code  = httpServletRequest.getParameter("code");
            String phone = httpServletRequest.getParameter("mobile");
            if(code ==null || code.trim().equals("")){
                myFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,new SmsCodeException("验证码不能为空"));
                 return;
            }
           Boolean blo = redisUtil.get(phone).toString().equals(code);
            if(!blo){
                myFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,new SmsCodeException("验证码错误"));
                return;
            }
        }
        //调用下一个过滤链
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
