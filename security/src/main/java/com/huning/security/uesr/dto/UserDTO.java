package com.huning.security.uesr.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

  private Long id;
  private String username;
  private String password;
  private int enabled;
}
