package com.huning.security.config;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.authorization.AuthorizationManagers.allOf;

import com.huning.security.entities.PageAuthorityEntity;
import com.huning.security.entities.PageEntity;
import com.huning.security.pages.service.PageService;
import com.huning.security.repositories.PageRepository;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager.Builder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AuthorizationManagerFactoryBean implements FactoryBean<RequestMatcherDelegatingAuthorizationManager> {


    private PageService pageService;

    private RequestMatcherDelegatingAuthorizationManager manager;

    @Override
    public RequestMatcherDelegatingAuthorizationManager getObject() throws Exception {

        if(manager == null){
            init();
        }

        return manager;
    }

    protected void init() {

        RequestMatcher permitAll =
          new OrRequestMatcher(
            new AntPathRequestMatcher("/contact"),
            new AntPathRequestMatcher("/register"),
            new AntPathRequestMatcher("/notices/test")
          );
        RequestMatcher authenticated =
          new OrRequestMatcher(
            new AntPathRequestMatcher("/"),
            new AntPathRequestMatcher("/user")
          );

        LinkedHashMap<RequestMatcher, AuthorityAuthorizationManager<RequestAuthorizationContext>> pageAuthorities = pageService.getPageAuthorities();

        Builder requestMatcherDelegatingAuthorizationManagerBuilder = RequestMatcherDelegatingAuthorizationManager.builder()
          .add(permitAll, (authentication, context) -> new AuthorizationDecision(true))
          .add(authenticated, new AuthenticatedAuthorizationManager<>())
          ;

        pageAuthorities.forEach(requestMatcherDelegatingAuthorizationManagerBuilder::add);

        manager = requestMatcherDelegatingAuthorizationManagerBuilder.build();
    }

    @Override
    public Class<?> getObjectType() {
        return RequestMatcherDelegatingAuthorizationManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setPageService(PageService service) {
        this.pageService = service;
    }
}
