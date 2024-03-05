package com.huning.security.contact_messages.controller;

import com.huning.security.repositories.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactMessageController {

  private final ContactMessageRepository contactMessageRepository;
}
