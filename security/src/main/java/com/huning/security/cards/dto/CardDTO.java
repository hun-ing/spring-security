package com.huning.security.cards.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

  private Long cardId;
  private String cardNumber;
  private Long customerId;
  private String cardType;
  private int totalLimit;
  private int amountUsed;
  private int availableAmount;
  private LocalDateTime createDt;
}
