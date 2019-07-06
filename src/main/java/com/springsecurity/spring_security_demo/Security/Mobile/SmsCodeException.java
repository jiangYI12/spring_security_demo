package com.springsecurity.spring_security_demo.Security.Mobile;

import org.springframework.security.core.AuthenticationException;

public class SmsCodeException extends AuthenticationException {
    public SmsCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public SmsCodeException(String msg) {
        super(msg);
    }
}
