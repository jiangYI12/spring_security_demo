package com.springsecurity.spring_security_demo.Security.Mobile;

import com.springsecurity.spring_security_demo.Util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 短信登录过滤器
 */
public class SmsCodeAuthenticationFilter  extends AbstractAuthenticationProcessingFilter {

    String  mobileParameter  = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;



    private boolean postOnly = true;

    protected SmsCodeAuthenticationFilter() {
        //需要拦截的路径
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST"));
    }

//    protected SmsCodeAuthenticationFilter(String defaultFilterProcessesUrl) {
//        //需要拦截的路径
//        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, "POST"));
//    }

    protected SmsCodeAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !httpServletRequest.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }

        String mobile = obtainMobile(httpServletRequest);

        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim()+"phones";
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

        // Allow subclasses to set the "details" property
        setDetails(httpServletRequest, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    /**
     * Provided so that subclasses may configure what is put into the
     * authentication request's details property.
     *
     * @param request
     *            that an authentication request is being created for
     * @param authRequest
     *            the authentication request object that should have its details
     *            set
     */
    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Sets the parameter name which will be used to obtain the username from
     * the login request.
     *
     * @param usernameParameter
     *            the parameter name. Defaults to "username".
     */

    //设置手机号
    public void setMobileParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Phone parameter must not be empty or null");
        this.mobileParameter = usernameParameter;
    }


    /**
     * Defines whether only HTTP POST requests will be allowed by this filter.
     * If set to true, and an authentication request is received which is not a
     * POST request, an exception will be raised immediately and authentication
     * will not be attempted. The <tt>unsuccessfulAuthentication()</tt> method
     * will be called as if handling a failed authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
    /**
     * 获取  参数名位mobile 手机号
     */
    protected String obtainMobile(HttpServletRequest request)
    {
        return request.getParameter(mobileParameter);
    }

    // 获取手机号
    public final String getMobileParameter() {
        return mobileParameter;
    }

}

