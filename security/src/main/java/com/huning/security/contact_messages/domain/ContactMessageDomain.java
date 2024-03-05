package com.huning.security.contact_messages.domain;

import com.huning.security.contact_messages.dto.ContactMessageDTO;
import com.huning.security.entities.ContactMessageEntity;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class ContactMessageDomain {

  private final String contactId;
  private final String contactName;
  private final String contactEmail;
  private final String subject;
  private final String message;
  private final LocalDateTime createDt;

  private ContactMessageDomain(ContactMessageDTO dto) {
    this.contactId = dto.getContactId();
    this.contactName = dto.getContactName();
    this.contactEmail = dto.getContactEmail();
    this.subject = dto.getSubject();
    this.message = dto.getMessage();
    this.createDt = dto.getCreateDt();
  }

  public static ContactMessageDomain of(ContactMessageDTO dto) {
    return new ContactMessageDomain(dto);
  }

  public ContactMessageEntity toCreateEntity() {
    return ContactMessageEntity.builder()
      .contactId(contactId)
      .contactName(contactName)
      .contactEmail(contactEmail)
      .subject(subject)
      .message(message)
      .createDt(createDt)
      .build();
  }
}
