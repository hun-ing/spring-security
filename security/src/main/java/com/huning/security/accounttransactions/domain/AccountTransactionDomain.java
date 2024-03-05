package com.huning.security.accounttransactions.domain;

import com.huning.security.accounttransactions.dto.AccountTransactionDTO;
import com.huning.security.entities.AccountTransactionEntity;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class AccountTransactionDomain {

  private final String transactionId;
  private final LocalDateTime transactionDt;
  private final String transactionSummary;
  private final String transactionType;
  private final int transactionAmt;
  private final int closingBalance;
  private final LocalDateTime createDt;

  private AccountTransactionDomain(AccountTransactionDTO dto) {
    this.transactionId = dto.getTransactionId();
    this.transactionDt = dto.getTransactionDt();
    this.transactionSummary = dto.getTransactionSummary();
    this.transactionType = dto.getTransactionType();
    this.transactionAmt = dto.getTransactionAmt();
    this.closingBalance = dto.getClosingBalance();
    this.createDt = dto.getCreateDt();
  }

  public static AccountTransactionDomain of(AccountTransactionDTO dto) {
    return new AccountTransactionDomain(dto);
  }

  public AccountTransactionEntity toCreateEntity() {
    return AccountTransactionEntity.builder()
      .transactionId(transactionId)
      .transactionDt(transactionDt)
      .transactionSummary(transactionSummary)
      .transactionType(transactionType)
      .transactionAmt(transactionAmt)
      .closingBalance(closingBalance)
      .createDt(createDt)
      .build();
  }
}
