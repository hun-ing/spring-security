package com.huning.security.pages.domain;

import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.PageEntity;
import com.huning.security.pages.dto.PageDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.Getter;


@Getter
public class PageDomain {

  private final Long pageId;
  private final String pageName;
  private final String pageUrl;
  private final LocalDateTime createDt;

  private PageDomain(PageDTO dto) {
    this.pageId = dto.getPageId();
    this.pageName = dto.getPageName();
    this.pageUrl = dto.getPageUrl();
    this.createDt = dto.getCreateDt();
  }

  public static PageDomain of(PageDTO dto) {
    return new PageDomain(dto);
  }

  public PageEntity toCreateEntity() {
    return PageEntity.builder()
      .pageId(pageId)
      .pageName(pageName)
      .pageUrl(pageUrl)
      .createDt(createDt)
      .pageAuthorities(new ArrayList<>())
      .build();
  }
}
