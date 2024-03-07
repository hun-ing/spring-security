package com.huning.security.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.repositories.CustomerRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ContextConfiguration
class LoginControllerTest {

  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  ;

  @BeforeEach
  @DisplayName("컨트롤러만 테스트한다.")
  void setup() {
//    this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(customerRepository, passwordEncoder)).build();
  }

  @BeforeEach
  @DisplayName("스프링 구성까지 포함하여 초기 세팅한다. (ex. 스프링 시큐리티")
  public void setUp(WebApplicationContext applicationContext) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
      .apply(springSecurity())
      .build();
  }

  @Test
  @DisplayName("정상 유저 생성 요청을 보낼 시 201 응답.")
  void test1() throws Exception {
    CustomerDTO customerDTO = CustomerDTO.builder()
      .name("test")
      .email("test@mail.com")
      .mobileNumber("01000000000")
      .pwd("test")
      .role("user")
      .build();

    String body = objectMapper.writeValueAsString(customerDTO);

    mockMvc.perform(
        post("/register").content(body).accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(content().string("Given user details are successfully registered"));
  }

  @Test
  @DisplayName("비정상 요청 시 500 에러 응답.")
  void test2() throws Exception {
    CustomerDTO customerDTO = CustomerDTO.builder()
      .name("test")
      .email("test@mail.com")
      .mobileNumber("01000000000")
      .role("user")
      .build();

    String body = objectMapper.writeValueAsString(customerDTO);

    MvcResult result = mockMvc.perform(
        post("/register").content(body).accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isInternalServerError())
      .andReturn();

    String content = result.getResponse().getContentAsString();

    assertThat(content).contains("An exception occurred due to ");
  }

  @Test
  @DisplayName("로그인 ")
  void test3() throws Exception {
    mockMvc.perform(
        post("/login")
          .with(csrf())
          .accept(MediaType.TEXT_HTML)
          .param("username", "test@mail.com")
          .param("password", "test")
      )
      .andDo(print())
      .andExpect(status().is3xxRedirection());
  }
}