package com.huning.security.controller;

import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.CustomerEntity;
import com.huning.security.repositories.CustomerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

  private final CustomerRepository customerRepository;

  @GetMapping("/myAccount")
  public AccountDTO getAccountDetails(@RequestParam Long customerId) {
    CustomerEntity customerEntity = customerRepository.findById(customerId)
      .orElseThrow(() -> new IllegalArgumentException("customer doesn't exist"));

    AccountEntity accountEntity = customerEntity.getAccount();

    if (accountEntity != null) {
      return AccountDTO.toCreateDTO(accountEntity);
    } else {
      return null;
    }
  }
}
