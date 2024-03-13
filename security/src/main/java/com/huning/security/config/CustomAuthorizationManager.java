package com.huning.security.config;

import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;

@RequiredArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

  private final RequestMatcherDelegatingAuthorizationManager manager;

  @Override
  public AuthorizationDecision check(Supplier<Authentication> authentication,
    RequestAuthorizationContext context) {
    return manager.check(authentication, context.getRequest());
  }
}
