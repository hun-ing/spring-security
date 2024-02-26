package com.huning.security.authority.controller;

import com.huning.security.authority.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorityController {

  private final AuthorityRepository authorityRepository;
}
