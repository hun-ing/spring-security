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
@Table(name = "cards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;
  private String cardNumber;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;
  private String cardType;
  private int totalLimit;
  private int amountUsed;
  private int availableAmount;
  private LocalDateTime createDt;

  //==연관관계 메서드==//
  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }

}


