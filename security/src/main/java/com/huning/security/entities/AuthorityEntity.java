package com.huning.security.entities;

import static jakarta.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "authorities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long authorityId;
  @OneToOne(mappedBy = "authority")
  private CustomerEntity customer; // customer entity
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL)
  private List<PageAuthorityEntity> pageAuthorities = new ArrayList<>();


  //==연관관계 메서드==//
  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }

  public void addPageAuthority(PageAuthorityEntity entity) {
    this.pageAuthorities.add(entity);
    entity.setAuthority(this);
  }
}


