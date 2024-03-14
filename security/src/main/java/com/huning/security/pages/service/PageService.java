package com.huning.security.pages.service;

import java.util.LinkedHashMap;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.RequestMatcher;

public interface PageService {

  LinkedHashMap<RequestMatcher, AuthorityAuthorizationManager<RequestAuthorizationContext>> getPageAuthorities();
}
