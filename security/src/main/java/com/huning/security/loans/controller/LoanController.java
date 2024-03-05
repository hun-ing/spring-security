package com.huning.security.loans.controller;

import com.huning.security.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanController {

  private final LoanRepository loanRepository;
}
