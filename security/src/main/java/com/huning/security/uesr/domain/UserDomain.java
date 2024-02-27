package com.huning.security.uesr.domain;

import com.huning.security.uesr.dto.UserDTO;
import com.huning.security.uesr.entity.UserEntity;
import lombok.Getter;


@Getter
public class UserDomain {

  private final Long id;
  private final String username;
  private final String password;
  private final int enabled;

  private UserDomain(UserDTO dto) {
    this.id = dto.getId();
    this.username = dto.getUsername();
    this.password = dto.getPassword();
    this.enabled = dto.getEnabled();
  }

  public static UserDomain of(UserDTO dto) {
    return new UserDomain(dto);
  }

  public UserEntity toCreateEntity() {
    return UserEntity.builder()
      .id(id)
      .username(username)
      .password(password)
      .enabled(enabled)
      .build();
  }
}
