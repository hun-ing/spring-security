package com.huning.security.contact_messages.dto;

import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.accounttransactions.dto.AccountTransactionDTO;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.ContactMessageEntity;
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

  public static ContactMessageDTO toCreateDTO(ContactMessageEntity entity) {
    if (entity == null) {
      return null;
    }

    return ContactMessageDTO.builder()
      .contactId(entity.getContactId())
      .contactName(entity.getContactName())
      .contactEmail(entity.getContactEmail())
      .subject(entity.getSubject())
      .message(entity.getMessage())
      .createDt(entity.getCreateDt())
      .build();
  }
}
