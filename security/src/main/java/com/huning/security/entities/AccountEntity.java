package com.huning.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity {

  @Id
  private Long accountNumber;
  @OneToOne(mappedBy = "account")
  private CustomerEntity customer; // Customer entity
  private String accountType;
  private String branchAddress;
  private LocalDateTime createDt;

  @JsonIgnore
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<AccountTransactionEntity> accountTransaction = new ArrayList<>();

  //==연관관계 메서드==//
  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }

  public void addAccountTransaction(AccountTransactionEntity accountTransaction) {
    this.accountTransaction.add(accountTransaction);
    accountTransaction.setAccount(this);
  }
}


