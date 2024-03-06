package com.huning.security.controller;

import com.huning.security.entities.LoanEntity;
import com.huning.security.loans.dto.LoanDTO;
import com.huning.security.repositories.LoanRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoansController {

  private final LoanRepository loanRepository;

  @GetMapping("/myLoans")
  public List<LoanDTO> getLoanDetails(@RequestParam Long customerId) {

    List<LoanEntity> loanEntityList = loanRepository.findAllByCustomerIdOrderByStartDtDesc(customerId);

    if (!loanEntityList.isEmpty()) {
      return loanEntityList.stream().map(LoanDTO::toCreateDTO).toList();
    } else {
      return null;
    }
  }
}
