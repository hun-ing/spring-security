package com.huning.security.customer.domain;

import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.CustomerEntity;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class CustomerDomain {

  private final Long customerId;
  private final String name;
  private final String email;
  private final String mobileNumber;
  private final String pwd;
  private final String role;
  private final LocalDateTime createDt;

  private CustomerDomain(CustomerDTO dto) {
    this.customerId = dto.getCustomerId();
    this.name = dto.getName();
    this.email = dto.getEmail();
    this.mobileNumber = dto.getMobileNumber();
    this.pwd = dto.getPwd();
    this.role = dto.getRole();
    this.createDt = dto.getCreateDt();
  }

  public static CustomerDomain of(CustomerDTO dto) {
    return new CustomerDomain(dto);
  }

  public CustomerEntity toCreateEntity() {
    return CustomerEntity.builder()
      .customerId(customerId)
      .name(name)
      .email(email)
      .mobileNumber(mobileNumber)
      .pwd(pwd)
      .role(role)
      .createDt(createDt)
      .build();
  }
}
