package com.huning.security.loans.dto;

import com.huning.security.contact_messages.dto.ContactMessageDTO;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.ContactMessageEntity;
import com.huning.security.entities.CustomerEntity;
import com.huning.security.entities.LoanEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

  private Long loanNumber;
  private CustomerDTO customer;
  private LocalDateTime startDt;
  private String loanType;
  private int totalLoan;
  private int amountPaid;
  private int outstandingAmount;
  private LocalDateTime createDt;


  public static LoanDTO toCreateDTO(LoanEntity entity) {
    if (entity == null) {
      return null;
    }

    return LoanDTO.builder()
      .loanNumber(entity.getLoanNumber())
      .customer(CustomerDTO.toCreateDTO(entity.getCustomer()))
      .startDt(entity.getStartDt())
      .loanType(entity.getLoanType())
      .totalLoan(entity.getTotalLoan())
      .amountPaid(entity.getAmountPaid())
      .outstandingAmount(entity.getOutstandingAmount())
      .build();
  }
}
