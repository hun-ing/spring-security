package com.huning.security.controller;

import com.huning.security.cards.dto.CardDTO;
import com.huning.security.entities.CardEntity;
import com.huning.security.repositories.CardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardsController {

  private final CardRepository cardRepository;

  @GetMapping("/myCards")
  public List<CardDTO> getCardDetails(@RequestParam Long customerId) {

    List<CardEntity> cardEntityList = cardRepository.findAllByCustomerId(customerId);

    if (!cardEntityList.isEmpty()) {
      return cardEntityList.stream().map(CardDTO::toCreateDTO).toList();
    } else {
      return null;
    }
  }
}
