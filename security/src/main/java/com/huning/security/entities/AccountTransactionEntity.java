package com.huning.security.entities;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Entity;
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
@Table(name = "account_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionEntity {

  // account entity
  // customer entity

  @Id
  private String transactionId;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "account_number")
  private AccountEntity account; // account entity
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer; // customer entity
  private LocalDateTime transactionDt;
  private String transactionSummary;
  private String transactionType;
  private int transactionAmt;
  private int closingBalance;
  private LocalDateTime createDt;

  //==연관관계 메서드==//
  public void setAccount(AccountEntity account) {
    this.account = account;
  }
  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }


}


