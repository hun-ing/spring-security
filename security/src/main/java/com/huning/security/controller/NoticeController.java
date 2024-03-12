package com.huning.security.controller;

import com.huning.security.entities.NoticeDetailEntity;
import com.huning.security.noticedetails.dto.NoticeDetailDTO;
import com.huning.security.repositories.NoticeDetailRepository;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeDetailRepository noticeDetailRepository;

  @GetMapping("/notices")
  public ResponseEntity<List<NoticeDetailDTO>> getNotices() {

    List<NoticeDetailEntity> noticeDetailEntities = noticeDetailRepository.findAllActiveNotices();

    if (!noticeDetailEntities.isEmpty()) {
      List<NoticeDetailDTO> detailDTOList = noticeDetailEntities.stream().map(NoticeDetailDTO::toCreateDTO)
        .toList();
      return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(detailDTOList);
    } else {
      return null;
    }
  }

  @GetMapping("/notices/test")
  public String test() {
    return "test";
  }
}
