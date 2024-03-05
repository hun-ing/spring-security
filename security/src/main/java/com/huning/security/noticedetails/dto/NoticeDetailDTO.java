package com.huning.security.noticedetails.dto;

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
}
