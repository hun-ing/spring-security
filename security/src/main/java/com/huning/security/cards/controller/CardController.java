package com.huning.security.cards.controller;

import com.huning.security.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardController {

  private final CardRepository cardRepository;
}
