package com.huning.security.contact_messages.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageDTO {

  private String contactId;
  private String contactName;
  private String contactEmail;
  private String subject;
  private String message;
  private LocalDateTime createDt;
}
