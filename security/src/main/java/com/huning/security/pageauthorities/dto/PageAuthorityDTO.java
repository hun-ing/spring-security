package com.huning.security.pageauthorities.dto;

import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.authorities.dto.AuthorityDTO;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.AuthorityEntity;
import com.huning.security.entities.PageAuthorityEntity;
import com.huning.security.pages.dto.PageDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageAuthorityDTO {

  private String pageAuthorityId;
  private PageDTO page;
  private AuthorityDTO authority;

  public static PageAuthorityDTO toCreateDTO(PageAuthorityEntity entity) {
    if (entity == null) {
      return null;
    }

    return PageAuthorityDTO.builder()
      .pageAuthorityId(entity.getPageAuthorityId())
      .page(PageDTO.toCreateDTO(entity.getPage()))
      .authority(AuthorityDTO.toCreateDTO(entity.getAuthority()))
      .build();
  }
}
