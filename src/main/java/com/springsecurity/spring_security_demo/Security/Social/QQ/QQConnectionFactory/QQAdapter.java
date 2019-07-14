package com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory;

import com.springsecurity.spring_security_demo.Security.Social.QQ.QQConnectionFactory.ServiceProvider.Api.QQ;
import com.springsecurity.spring_security_demo.Security.Social.QQ.User.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
//编写QQ API适配器，将从QQ API拿到的用户数据模型转换为Spring Social的标准用户数据模型
//构建ApiAdapter
//QQ Api类型
public class QQAdapter implements ApiAdapter<QQ> {

    //测试当前QQ是否可用
    @Override
    public boolean test(QQ api) {
        //判断QQ是否可用
        return true;
    }

    // 创建 Connection 所需要的适配项
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        //显示用户名字
        values.setDisplayName(userInfo.getNickname());
        //用户头像
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        //个人主页URL
        values.setProfileUrl(null);
        //服务商用户ID openID
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do noting
        // 更行微博(如果是)
    }
}
