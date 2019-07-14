package com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory;


import com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory.ServiceProvider.Api.QQ;
import com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory.ServiceProvider.Api.QQImpl;
import com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory.ServiceProvider.QQOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

//编写QQ的OAuth2流程处理器的提供器
//构建 ServiceProvider
//QQ 指的Api接口类型
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    //appid
    private String appId;
    //引导用户跳转的地址
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    //申请令牌地址
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        //传入 OAuth2Template 实现
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }

}
