package com.huning.security.pageauthorities.service;

import com.huning.security.repositories.PageAuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageAuthorityServiceImpl {

  private final PageAuthorityRepository pageAuthorityRepository;
}
