package com.huning.security.cards.dto;

import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.accounttransactions.dto.AccountTransactionDTO;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.CardEntity;
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
  private CustomerDTO customer;
  private String cardType;
  private int totalLimit;
  private int amountUsed;
  private int availableAmount;
  private LocalDateTime createDt;

  public static CardDTO toCreateDTO(CardEntity entity) {
    if (entity == null) {
      return null;
    }

    return CardDTO.builder()
      .cardId(entity.getCardId())
      .cardNumber(entity.getCardNumber())
      .customer(CustomerDTO.toCreateDTO(entity.getCustomer()))
      .cardType(entity.getCardType())
      .totalLimit(entity.getTotalLimit())
      .amountUsed(entity.getAmountUsed())
      .availableAmount(entity.getAvailableAmount())
      .build();
  }
}
