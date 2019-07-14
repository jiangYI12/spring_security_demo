package com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory;

import com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory.ServiceProvider.Api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

//QQ链接工产
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
