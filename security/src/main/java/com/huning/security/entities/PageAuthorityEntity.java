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
@Table(name = "page_authorities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PageAuthorityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String pageAuthorityId;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "page_id")
  private PageEntity page; // account entity
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "authority_id")
  private AuthorityEntity authority; // customer entity

  //==연관관계 메서드==//
  public void setPage(PageEntity page) {
    this.page = page;
  }
  public void setAuthority(AuthorityEntity authority) {
    this.authority = authority;
  }
}


