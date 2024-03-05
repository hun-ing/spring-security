package com.huning.security.authority.domain;

import com.huning.security.authority.dto.AuthorityDTO;
import com.huning.security.entities.AuthorityEntity;
import lombok.Getter;


@Getter
public class AuthorityDomain {

  private final Long id;
  private final String username;
  private final String authority;

  private AuthorityDomain(AuthorityDTO dto) {
    this.id = dto.getId();
    this.username = dto.getUsername();
    this.authority = dto.getAuthority();
  }

  public static AuthorityDomain of(AuthorityDTO dto) {
    return new AuthorityDomain(dto);
  }

  public AuthorityEntity toCreateEntity() {
    return AuthorityEntity.builder()
      .id(id)
      .username(username)
      .authority(authority)
      .build();
  }
}
