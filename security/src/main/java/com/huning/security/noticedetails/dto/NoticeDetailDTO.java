package com.huning.security.noticedetails.dto;

import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.CustomerEntity;
import com.huning.security.entities.NoticeDetailEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDetailDTO {

  private Long noticeId;
  private String noticeSummary;
  private String noticeDetails;
  private LocalDateTime noticeBegDt;
  private LocalDateTime noticeEndDt;
  private LocalDateTime createDt;
  private LocalDateTime updateDt;

  public static NoticeDetailDTO toCreateDTO(NoticeDetailEntity entity) {
    if (entity == null) {
      return null;
    }

    return NoticeDetailDTO.builder()
      .noticeId(entity.getNoticeId())
      .noticeSummary(entity.getNoticeSummary())
      .noticeDetails(entity.getNoticeDetails())
      .noticeBegDt(entity.getNoticeBegDt())
      .noticeEndDt(entity.getNoticeEndDt())
      .createDt(entity.getCreateDt())
      .build();
  }
}
