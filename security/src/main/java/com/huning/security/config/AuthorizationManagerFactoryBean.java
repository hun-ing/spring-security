package com.huning.security.config;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class AuthorizationManagerFactoryBean implements FactoryBean<RequestMatcherDelegatingAuthorizationManager> {

    private RequestMatcherDelegatingAuthorizationManager manager;

    @Override
    public RequestMatcherDelegatingAuthorizationManager getObject() throws Exception {

        if(manager == null){
            init();
        }

        return manager;
    }

    private void init() {
        RequestMatcher permitAll =
          new OrRequestMatcher(
            new AntPathRequestMatcher("/contact"),
            new AntPathRequestMatcher("/register"),
            new AntPathRequestMatcher("/notices")
          );
        RequestMatcher authenticated =
          new OrRequestMatcher(
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/user")
          );
        RequestMatcher myAccount = new AntPathRequestMatcher("/myAccount");
        RequestMatcher myBalance = new AntPathRequestMatcher("/myBalance");
        RequestMatcher myLoans = new AntPathRequestMatcher("/myLoans");
        RequestMatcher myCards = new AntPathRequestMatcher("/myCards");

        manager = RequestMatcherDelegatingAuthorizationManager.builder()
          .add(permitAll, (authentication, context) -> new AuthorizationDecision(true))
          .add(myAccount, AuthorityAuthorizationManager.hasRole("ADMIN"))
          .add(myBalance, AuthorityAuthorizationManager.hasAnyRole("USER", "ADMIN"))
          .add(myLoans, AuthorityAuthorizationManager.hasRole("ADMIN"))
          .add(myCards, AuthorityAuthorizationManager.hasRole("ADMIN"))
          .add(authenticated, new AuthenticatedAuthorizationManager<>())
          .build();
    }

    @Override
    public Class<?> getObjectType() {
        return RequestMatcherDelegatingAuthorizationManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
