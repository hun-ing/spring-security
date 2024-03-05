package com.huning.security.accounttransactions.dto;

import com.huning.security.entities.AccountEntity;
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
public class AccountTransactionDTO {

  private String transactionId;
  private AccountEntity account;
  private CustomerEntity customer;
  private LocalDateTime transactionDt;
  private String transactionSummary;
  private String transactionType;
  private int transactionAmt;
  private int closingBalance;
  private LocalDateTime createDt;
}
