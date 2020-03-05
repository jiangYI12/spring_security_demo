package com.springsecurity.spring_security_demo.Security;

import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;

import java.util.Set;

public class MyConnectionFactoryLocator implements ConnectionFactoryLocator {

    @Override
    public ConnectionFactory<?> getConnectionFactory(String s) {
        return null;
    }

    @Override
    public <A> ConnectionFactory<A> getConnectionFactory(Class<A> aClass) {
        return null;
    }

    @Override
    public Set<String> registeredProviderIds() {
        return null;
    }
}
