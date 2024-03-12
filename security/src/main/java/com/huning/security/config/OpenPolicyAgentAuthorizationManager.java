package com.huning.security.config;

import java.util.function.Supplier;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

//@Component
public class OpenPolicyAgentAuthorizationManager implements
  AuthorizationManager<RequestAuthorizationContext> {

  @Override
  public AuthorizationDecision check(Supplier<Authentication> authentication,
    RequestAuthorizationContext object) {
    return null;
  }
}
