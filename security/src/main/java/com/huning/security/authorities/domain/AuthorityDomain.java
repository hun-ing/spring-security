package com.huning.security.authorities.domain;

import com.huning.security.authorities.dto.AuthorityDTO;
import com.huning.security.entities.AuthorityEntity;
import lombok.Getter;


@Getter
public class AuthorityDomain {

  private final Long id;
  private final String name;

  private AuthorityDomain(AuthorityDTO dto) {
    this.id = dto.getId();
    this.name = dto.getName();
  }

  public static AuthorityDomain of(AuthorityDTO dto) {
    return new AuthorityDomain(dto);
  }

  public AuthorityEntity toCreateEntity() {
    return AuthorityEntity.builder()
      .id(id)
      .name(name)
      .build();
  }
}
