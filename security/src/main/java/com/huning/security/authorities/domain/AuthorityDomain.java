package com.huning.security.authorities.domain;

import com.huning.security.authorities.dto.AuthorityDTO;
import com.huning.security.entities.AuthorityEntity;
import java.util.ArrayList;
import lombok.Getter;


@Getter
public class AuthorityDomain {

  private final Long authorityId;
  private final String name;

  private AuthorityDomain(AuthorityDTO dto) {
    this.authorityId = dto.getAuthorityId();
    this.name = dto.getName();
  }

  public static AuthorityDomain of(AuthorityDTO dto) {
    return new AuthorityDomain(dto);
  }

  public AuthorityEntity toCreateEntity() {
    return AuthorityEntity.builder()
      .authorityId(authorityId)
      .name(name)
      .pageAuthorities(new ArrayList<>())
      .build();
  }
}
