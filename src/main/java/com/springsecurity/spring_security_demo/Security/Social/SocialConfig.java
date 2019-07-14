package com.springsecurity.spring_security_demo.Security.Social;

import com.springsecurity.spring_security_demo.Security.Social.Binding.ConnectView;
import com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory.QQConnectionFactory;
import com.springsecurity.spring_security_demo.Security.Social.QQ.SocialProperties;
import com.springsecurity.spring_security_demo.Security.Social.WeChat.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@EnableSocial
//配置数据库
public class SocialConfig implements SocialConfigurer {

    //数据源
    @Resource
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    //TODO
    @Override
    public UserIdSource getUserIdSource() {
        // TODO Auto-generated method stub
        return new AuthenticationNameUserIdSource();
    }

//connectionFactoryLocator 查找connectionFactory
// Encryptors.noOpText() 插入数据库加解密
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // connectionFactoryLocator 查找connectionFactory  Encryptors 加解密
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        //QQ 创建数据库表前缀 如数据库中表的前缀 QQ_UserConnection **_UserConnection
//        repository.setTablePrefix("QQ");
        if(connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }
    // 加入到SpringSercurity 过滤器链中
   //创建后将过滤器加入大=到springSecurity过滤器链中
    @Bean
    public SpringSocialConfigurer socialSecurityConfig() {
        //修改 默认拦截器链接
        //获取拦截的URL
        String filterProcessesUrl = SocialProperties.filterProcessesUrl;
        MySpringSocialConfigurer configurer = new MySpringSocialConfigurer(filterProcessesUrl);
        //查找不到用户跳转的地址
        configurer.signupUrl("/user/registerpage");
        return configurer;
    }

    //拿到第三方登陆用户信息
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }

    /**
     * autoconfigure2.04中已经不存在social的自动配置类了
     * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                   Environment environment) {
//    configurer.addConnectionFactory(QQCreateConnectionFactory());
    configurer.addConnectionFactory(WechatCreateConnectionFactory());
    configurer.addConnectionFactory(QQCreateConnectionFactory());
    }


    public ConnectionFactory<?> QQCreateConnectionFactory() {
    return new QQConnectionFactory("callback.do", "100550231","69b6ab57b22f3c2fe6a6149274e3295e");
    }
    public ConnectionFactory<?> WechatCreateConnectionFactory() {
        return new WeixinConnectionFactory("weixin", "wxd99431bbff8305a0","60f78681d063590a469f1b297feff3c4");
    }

    //微信绑定成功页面
    @Bean("connection/weixinConnection")
    public View weiXingConnectionView(){
        return  new ConnectView();
    }
}