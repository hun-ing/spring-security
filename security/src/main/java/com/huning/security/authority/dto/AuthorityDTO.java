package com.huning.security.authority.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorityDTO {

  private Long id;
  private String username;
  private String authority;
}
