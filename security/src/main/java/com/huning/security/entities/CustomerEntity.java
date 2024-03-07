package com.huning.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;
  private String name;
  private String email;
  private String mobileNumber;
  private String pwd;
  private String role;
  private LocalDateTime createDt;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_number")
  private AccountEntity account; // Customer entity

  @JsonIgnore
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<AccountTransactionEntity> accountTransactions = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<CardEntity> cards = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<AuthorityEntity> authorities;

  //==연관관계 메서드==//
  public void setAccount(AccountEntity account) {
    this.account = account;
  }

  public void addAccountTransaction(AccountTransactionEntity entity) {
    this.accountTransactions.add(entity);
    entity.setCustomer(this);
  }

  public void addCard(CardEntity entity) {
    this.cards.add(entity);
    entity.setCustomer(this);
  }

  public void addAuthority(AuthorityEntity entity) {
    this.authorities.add(entity);
    entity.setCustomer(this);
  }
}


