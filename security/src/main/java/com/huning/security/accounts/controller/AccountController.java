package com.huning.security.accounts.controller;

import com.huning.security.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final AccountRepository accountRepository;
}
