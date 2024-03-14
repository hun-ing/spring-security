package com.huning.security.pages.service;

import com.huning.security.entities.AuthorityEntity;
import com.huning.security.entities.PageAuthorityEntity;
import com.huning.security.entities.PageEntity;
import com.huning.security.repositories.PageAuthorityRepository;
import com.huning.security.repositories.PageRepository;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService{

  private final PageRepository pageRepository;


  @Override
  @Transactional
  public LinkedHashMap<RequestMatcher, AuthorityAuthorizationManager<RequestAuthorizationContext>> getPageAuthorities() {
    LinkedHashMap<RequestMatcher, AuthorityAuthorizationManager<RequestAuthorizationContext>> result = new LinkedHashMap<>();

    List<PageEntity> pageEntities = pageRepository.findAll();

    pageEntities.forEach(pageEntity -> {
      RequestMatcher requestMatcher = new AntPathRequestMatcher(pageEntity.getPageUrl());

      String[] roles = pageEntity.getPageAuthorities().stream()
        .map(PageAuthorityEntity::getAuthority).map(AuthorityEntity::getName).toArray(String[]::new);

      AuthorityAuthorizationManager<RequestAuthorizationContext> authorizationManager = AuthorityAuthorizationManager.hasAnyRole(roles);

      result.put(requestMatcher, authorizationManager);
    });

    return result;
  }
}
