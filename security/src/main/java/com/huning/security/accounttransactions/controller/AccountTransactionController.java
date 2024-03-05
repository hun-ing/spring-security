package com.huning.security.accounttransactions.controller;

import com.huning.security.repositories.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountTransactionController {

  private final AccountTransactionRepository accountTransactionRepository;
}
