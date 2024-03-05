package com.huning.security.noticedetails.domain;

import com.huning.security.noticedetails.dto.NoticeDetailDTO;
import com.huning.security.entities.NoticeDetailEntity;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class NoticeDetailDomain {

  private final Long noticeId;
  private final String noticeSummary;
  private final String noticeDetails;
  private final LocalDateTime noticeBegDt;
  private final LocalDateTime noticeEndDt;
  private final LocalDateTime createDt;
  private final LocalDateTime updateDt;

  private NoticeDetailDomain(NoticeDetailDTO dto) {
    this.noticeId = dto.getNoticeId();
    this.noticeSummary = dto.getNoticeSummary();
    this.noticeDetails = dto.getNoticeDetails();
    this.noticeBegDt = dto.getNoticeBegDt();
    this.noticeEndDt = dto.getNoticeEndDt();
    this.createDt = dto.getCreateDt();
    this.updateDt = dto.getUpdateDt();
  }

  public static NoticeDetailDomain of(NoticeDetailDTO dto) {
    return new NoticeDetailDomain(dto);
  }

  public NoticeDetailEntity toCreateEntity() {
    return NoticeDetailEntity.builder()
      .noticeId(noticeId)
      .noticeSummary(noticeSummary)
      .noticeDetails(noticeDetails)
      .noticeBegDt(noticeBegDt)
      .noticeEndDt(noticeEndDt)
      .createDt(createDt)
      .updateDt(updateDt)
      .build();
  }
}
