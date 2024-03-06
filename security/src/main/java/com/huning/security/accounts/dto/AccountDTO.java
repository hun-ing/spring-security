package com.huning.security.accounts.dto;

import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

  private Long accountNumber;
  private CustomerDTO customer;
  private String accountType;
  private String branchAddress;
  private LocalDateTime createDt;

  public static AccountDTO toCreateDTO(AccountEntity entity) {
    if (entity == null) {
      return null;
    }

    return AccountDTO.builder()
      .accountNumber(entity.getAccountNumber())
      .customer(CustomerDTO.toCreateDTO(entity.getCustomer()))
      .accountType(entity.getAccountType())
      .branchAddress(entity.getBranchAddress())
      .createDt(entity.getCreateDt())
      .build();
  }
}
