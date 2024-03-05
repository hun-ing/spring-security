package com.huning.security.entities;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "loans")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long loanNumber;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;
  private LocalDateTime startDt;
  private String loanType;
  private int totalLoan;
  private int amountPaid;
  private int outstandingAmount;
  private LocalDateTime createDt;

  //==연관관계 메서드==//
  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }
}


