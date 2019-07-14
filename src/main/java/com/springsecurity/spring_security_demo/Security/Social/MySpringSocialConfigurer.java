package com.springsecurity.spring_security_demo.Security.Social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

//自定义配置
public class MySpringSocialConfigurer extends SpringSocialConfigurer {
    //拦截路径
    private String filterProcessesUrl;

    public MySpringSocialConfigurer() {
        super();
    }

    public MySpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
