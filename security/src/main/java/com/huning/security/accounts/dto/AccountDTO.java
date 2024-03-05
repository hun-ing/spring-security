package com.huning.security.accounts.dto;

import com.huning.security.customer.dto.CustomerDTO;
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
}
