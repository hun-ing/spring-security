package com.huning.security.cards.domain;

import com.huning.security.cards.dto.CardDTO;
import com.huning.security.entities.CardEntity;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class CardDomain {

  private final Long cardId;
  private final String cardNumber;
  private final Long customerId;
  private final String cardType;
  private final int totalLimit;
  private final int amountUsed;
  private final int availableAmount;
  private final LocalDateTime createDt;

  private CardDomain(CardDTO dto) {
    this.cardId = dto.getCardId();
    this.cardNumber = dto.getCardNumber();
    this.customerId = dto.getCustomerId();
    this.cardType = dto.getCardType();
    this.totalLimit = dto.getTotalLimit();
    this.amountUsed = dto.getAmountUsed();
    this.availableAmount = dto.getAvailableAmount();
    this.createDt = dto.getCreateDt();
  }

  public static CardDomain of(CardDTO dto) {
    return new CardDomain(dto);
  }

  public CardEntity toCreateEntity() {
    return CardEntity.builder()
      .cardId(cardId)
      .cardNumber(cardNumber)
      .customerId(customerId)
      .cardType(cardType)
      .totalLimit(totalLimit)
      .amountUsed(amountUsed)
      .availableAmount(availableAmount)
      .createDt(createDt)
      .build();
  }
}
