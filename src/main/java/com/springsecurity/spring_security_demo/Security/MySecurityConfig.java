package com.springsecurity.spring_security_demo.Security;

import com.springsecurity.spring_security_demo.Security.Mobile.SmsCodeAuthenticationFilter;
import com.springsecurity.spring_security_demo.Security.Mobile.SmsCodeAuthenticationSecurityConfig;
import com.springsecurity.spring_security_demo.Security.Mobile.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private DataSource dataSource;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;
    @Autowired
    PersistentTokenRepository persistentTokenRepository;
    @Autowired
    AuthenticationSuccessHandler mySuccessHandler;
    @Autowired
    AuthenctiationFailureHandler myFailureHandler;
    @Autowired
    SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    SmsCodeFilter smsCodeFilter;
    //密码加密
    @Bean(name = "myPasswordEncoder")
    @Scope(scopeName = "prototype")
    public PasswordEncoder passwordEncoder(){
        System.err.println(new BCryptPasswordEncoder().encode("sdad23307216"));
        return new BCryptPasswordEncoder();
    }
    // 记住我
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        tokenRepository.setDataSource(dataSource);
        //启动的时候创建记住我的表
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略前端资源
        web.ignoring().antMatchers(HttpMethod.GET, "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.gif", "/**/*.jpg");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/user")
                .successHandler(mySuccessHandler)//登录成功跳转
//              .failureUrl("/index/failerlogin")//登录失败跳转
                .failureHandler(myFailureHandler)//登录失败跳转
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .authorizeRequests()
                .antMatchers("/index/welcome").hasRole("admin")
                .antMatchers("/index/welcomeTwo").hasRole("user")
                .antMatchers("/login/*","/authentication/mobile","/login","/index/failerlogin").permitAll()
                .anyRequest()
                .authenticated()//所有路径都需要登录
                .and()
//              权限不足返回信息
                .exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler)
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository)//记住我数据源
                .tokenValiditySeconds(10000)//过期秒数
                .userDetailsService(myUserDetailService)
        //添加短信验证
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
        //添加自定义过滤器
                .and().addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
        ;

    }
}
