package com.huning.security.loans.dto;

import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.CustomerEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

  private Long loanNumber;
  private CustomerDTO customer;
  private LocalDateTime startDt;
  private String loanType;
  private int totalLoan;
  private int amountPaid;
  private int outstandingAmount;
  private LocalDateTime createDt;
}
