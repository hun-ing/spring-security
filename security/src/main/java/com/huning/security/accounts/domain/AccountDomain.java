package com.huning.security.accounts.domain;

import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.entities.AccountEntity;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class AccountDomain {

  private final Long accountNumber;
  private final String accountType;
  private final String branchAddress;
  private final LocalDateTime createDt;

  private AccountDomain(AccountDTO dto) {
    this.accountNumber = dto.getAccountNumber();
    this.accountType = dto.getAccountType();
    this.branchAddress = dto.getBranchAddress();
    this.createDt = dto.getCreateDt();
  }

  public static AccountDomain of(AccountDTO dto) {
    return new AccountDomain(dto);
  }

  public AccountEntity toCreateEntity() {
    return AccountEntity.builder()
      .accountNumber(accountNumber)
      .accountType(accountType)
      .branchAddress(branchAddress)
      .createDt(createDt)
      .build();
  }
}
