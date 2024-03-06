package com.huning.security.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huning.security.repositories.NoticeDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ContextConfiguration
class NoticeControllerTest {

  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private NoticeDetailRepository noticeDetailRepository;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new NoticeController(noticeDetailRepository))
      .build();
  }

  @Test
  @DisplayName("GET /notices 요청 시 데이터가 있으면 Json List, 없으면 null 반환")
  void getNotices() throws Exception {
    MvcResult result = mockMvc.perform(
        get("/notices"))
      .andDo(print())
      .andExpect(status().isOk())
      .andReturn();

    String content = result.getResponse().getContentAsString();

    if (!content.isEmpty()) {
      assertDoesNotThrow(() -> objectMapper.readValue(content, new TypeReference<>() {}));
    }
  }
}