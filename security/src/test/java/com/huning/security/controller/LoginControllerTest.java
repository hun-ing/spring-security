package com.huning.security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ContextConfiguration
class LoginControllerTest {

  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CustomerRepository customerRepository;

  @BeforeEach
  void setup() {
//    this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(customerRepository)).build();
  }

  @Test
  @DisplayName("유저 생성 요청을 보낼 시 201 응답을 받는다.")
  void registerUser() throws Exception {
    String body = objectMapper.writeValueAsString(
      CustomerDTO.builder().email("test").pwd("test").role("admin").build());

    mockMvc.perform(post("/register").content(body).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isCreated());
  }
}