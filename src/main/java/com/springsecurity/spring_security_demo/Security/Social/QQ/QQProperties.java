package com.springsecurity.spring_security_demo.Security.Social.QQ;

import lombok.Data;

//无用 配置类
@Data
public class QQProperties extends SocialProperties
{
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
