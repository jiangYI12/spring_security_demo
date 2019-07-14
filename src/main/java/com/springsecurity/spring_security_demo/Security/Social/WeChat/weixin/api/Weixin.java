/**
 * 
 */
package com.springsecurity.spring_security_demo.Security.Social.WeChat.weixin.api;

/**
 * 微信API调用接口
 *
 */
public interface Weixin {

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);
	
}
