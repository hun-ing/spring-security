package com.huning.security.accounttransactions.dto;

import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.AccountTransactionEntity;
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
  private AccountDTO account;
  private CustomerDTO customer;
  private LocalDateTime transactionDt;
  private String transactionSummary;
  private String transactionType;
  private int transactionAmt;
  private int closingBalance;
  private LocalDateTime createDt;

  public static AccountTransactionDTO toCreateDTO(AccountTransactionEntity entity) {
    if (entity == null) {
      return null;
    }

    return AccountTransactionDTO.builder()
      .transactionId(entity.getTransactionId())
      .account(AccountDTO.toCreateDTO(entity.getAccount()))
      .customer(CustomerDTO.toCreateDTO(entity.getCustomer()))
      .transactionDt(entity.getTransactionDt())
      .transactionSummary(entity.getTransactionSummary())
      .transactionType(entity.getTransactionType())
      .transactionAmt(entity.getTransactionAmt())
      .closingBalance(entity.getClosingBalance())
      .createDt(entity.getCreateDt())
      .build();
  }
}
