package com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory.ServiceProvider.Api;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.springsecurity.spring_security_demo.Security.Social.QQ.User.QQUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

//编写Api 获取用户信息
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    //拿去openID路径 用户唯一标识 如qq号
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    // 获取用户信息路径
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    //注册的appid
    private String appId;

     //用户QQ号码
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    //accessToken 用户令牌
    public QQImpl(String accessToken, String appId) {
    //调用父类构造方法， TokenStrategy.ACCESS_TOKEN_PARAMETER 当调用父类 restTemplate
        //请求token时会将 accessToken 作为查询参数发请求
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        // 获取 openid
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        //处理OpenId
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }


 // 获取用户信息
    @Override
    public QQUserInfo getUserInfo() {

        String url = String.format(URL_GET_USERINFO, appId, openId);
        //发请求获取用户信息
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        QQUserInfo userInfo = null;
        try {
            //将Json转成UserInfo
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException("获取用户信息失败", e);
        }
    }

}
