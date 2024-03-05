package com.huning.security.noticedetails.controller;

import com.huning.security.repositories.NoticeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeDetailController {

  private final NoticeDetailRepository noticeDetailRepository;
}
