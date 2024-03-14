package com.huning.security.authorities.dto;

import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.AuthorityEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorityDTO {

  private Long authorityId;
  private String name;

  public static AuthorityDTO toCreateDTO(AuthorityEntity entity) {
    if (entity == null) {
      return null;
    }

    return AuthorityDTO.builder()
      .authorityId(entity.getAuthorityId())
      .name(entity.getName())
      .build();
  }
}
