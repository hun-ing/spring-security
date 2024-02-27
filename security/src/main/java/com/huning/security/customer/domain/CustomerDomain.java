package com.huning.security.customer.domain;

import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.customer.entity.CustomerEntity;
import lombok.Getter;


@Getter
public class CustomerDomain {

  private final Long id;
  private final String email;
  private final String pwd;
  private final String role;

  private CustomerDomain(CustomerDTO dto) {
    this.id = dto.getId();
    this.email = dto.getEmail();
    this.pwd = dto.getPwd();
    this.role = dto.getRole();
  }

  public static CustomerDomain of(CustomerDTO dto) {
    return new CustomerDomain(dto);
  }

  public CustomerEntity toCreateEntity() {
    return CustomerEntity.builder()
      .id(id)
      .email(email)
      .pwd(pwd)
      .role(role)
      .build();
  }
}
