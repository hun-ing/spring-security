package com.huning.security.customer.dto;

import com.huning.security.accounts.dto.AccountDTO;
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
public class CustomerDTO {

  private Long customerId;
  private String name;
  private String email;
  private String mobileNumber;
  private String pwd;
  private String role;
  private LocalDateTime createDt;

  public static CustomerDTO toCreateDTO(CustomerEntity entity) {
    if (entity == null) {
      return null;
    }

    return CustomerDTO.builder()
      .customerId(entity.getCustomerId())
      .name(entity.getName())
      .email(entity.getEmail())
      .mobileNumber(entity.getMobileNumber())
      .pwd(entity.getPwd())
      .role(entity.getRole())
      .createDt(entity.getCreateDt())
      .build();
  }
}
