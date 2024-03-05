package com.huning.security.customer.dto;

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
}
