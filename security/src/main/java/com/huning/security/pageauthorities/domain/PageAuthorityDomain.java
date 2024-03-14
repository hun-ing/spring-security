package com.huning.security.pageauthorities.domain;

import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.PageAuthorityEntity;
import com.huning.security.pageauthorities.dto.PageAuthorityDTO;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class PageAuthorityDomain {

  private final String pageAuthorityId;

  private PageAuthorityDomain(PageAuthorityDTO dto) {
    this.pageAuthorityId = dto.getPageAuthorityId();
  }

  public static PageAuthorityDomain of(PageAuthorityDTO dto) {
    return new PageAuthorityDomain(dto);
  }

  public PageAuthorityEntity toCreateEntity() {
    return PageAuthorityEntity.builder()
      .pageAuthorityId(pageAuthorityId)
      .build();
  }
}
