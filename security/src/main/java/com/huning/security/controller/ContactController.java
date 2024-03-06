package com.huning.security.controller;

import com.huning.security.contact_messages.domain.ContactMessageDomain;
import com.huning.security.contact_messages.dto.ContactMessageDTO;
import com.huning.security.entities.ContactMessageEntity;
import com.huning.security.repositories.ContactMessageRepository;
import com.huning.security.repositories.CustomerRepository;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

  private final ContactMessageRepository contactMessageRepository;

  @GetMapping("/contact")
  public ContactMessageDTO saveContactInquiryDetails(@RequestBody ContactMessageDTO contactMessageDTO) {
    contactMessageDTO.setContactId(getServiceReqNumber());
    contactMessageDTO.setCreateDt(LocalDateTime.now());
    ContactMessageEntity contactMessageEntity = contactMessageRepository.save(
      ContactMessageDomain.of(contactMessageDTO).toCreateEntity());

    return ContactMessageDTO.toCreateDTO(contactMessageEntity);
  }

  private String getServiceReqNumber() {
    Random random = new Random();
    int ranNum = random.nextInt(999999999 - 9999) + 9999;
    return "SR" + ranNum;
  }
}
