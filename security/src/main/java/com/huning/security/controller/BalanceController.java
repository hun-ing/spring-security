package com.huning.security.controller;

import com.huning.security.accounttransactions.dto.AccountTransactionDTO;
import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.CustomerEntity;
import com.huning.security.repositories.AccountTransactionRepository;
import com.huning.security.repositories.CustomerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {

  private final AccountTransactionRepository accountTransactionRepository;

  @GetMapping("/myBalance")
  public List<AccountTransactionDTO> getBalanceDetails(@RequestParam Long customerId) {

    List<AccountTransactionEntity> accountTransactionEntityList = accountTransactionRepository.findByCustomerIdOrderByTransactionDtDesc(customerId);

    if (!accountTransactionEntityList.isEmpty()) {
      return accountTransactionEntityList.stream().map(AccountTransactionDTO::toCreateDTO).toList();
    } else {
      return null;
    }
  }
}
