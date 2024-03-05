package com.huning.security.loans.domain;

import com.huning.security.loans.dto.LoanDTO;
import com.huning.security.entities.LoanEntity;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class LoanDomain {

  private final Long loanNumber;
  private final LocalDateTime startDt;
  private final String loanType;
  private final int totalLoan;
  private final int amountPaid;
  private final int outstandingAmount;
  private final LocalDateTime createDt;

  private LoanDomain(LoanDTO dto) {
    this.loanNumber = dto.getLoanNumber();
    this.startDt = dto.getStartDt();
    this.loanType = dto.getLoanType();
    this.totalLoan = dto.getTotalLoan();
    this.amountPaid = dto.getAmountPaid();
    this.outstandingAmount = dto.getOutstandingAmount();
    this.createDt = dto.getCreateDt();
  }

  public static LoanDomain of(LoanDTO dto) {
    return new LoanDomain(dto);
  }

  public LoanEntity toCreateEntity() {
    return LoanEntity.builder()
      .loanNumber(loanNumber)
      .startDt(startDt)
      .loanType(loanType)
      .totalLoan(totalLoan)
      .amountPaid(amountPaid)
      .outstandingAmount(outstandingAmount)
      .createDt(createDt)
      .build();
  }
}
