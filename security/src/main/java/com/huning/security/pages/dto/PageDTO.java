package com.huning.security.pages.dto;

import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.PageEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {

  private Long pageId;
  private String pageName;
  private String pageUrl;
  private LocalDateTime createDt;

  public static PageDTO toCreateDTO(PageEntity entity) {
    if (entity == null) {
      return null;
    }

    return PageDTO.builder()
      .pageId(entity.getPageId())
      .pageName(entity.getPageName())
      .pageUrl(entity.getPageUrl())
      .createDt(entity.getCreateDt())
      .build();
  }
}
