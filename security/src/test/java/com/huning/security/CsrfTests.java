package com.huning.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.huning.security.config.SecurityConfig;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ContextConfiguration
public class CsrfTests {

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp(WebApplicationContext applicationContext) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
      .apply(springSecurity())
      .build();
  }

  @Test
  public void test() throws Exception {
//    MvcResult mvcResult1 = this.mockMvc.perform(get("/"))
//        .andDo(print())
//        .andReturn();
//
//    String CSRF_VALUE = Objects.requireNonNull(mvcResult1.getResponse().getCookie("XSRF-TOKEN")).getValue();

    this.mockMvc.perform(post("/login")
            .accept(MediaType.TEXT_HTML).with(csrf())
//            .header("X-XSRF-TOKEN", CSRF_VALUE)
            .param("username", "test")
            .param("password", "test"))
        .andDo(print())
        .andExpect(status().is3xxRedirection())
        .andExpect(header().string(HttpHeaders.LOCATION, "/login?error"));
  }

  @Test
  public void loginWhenValidCsrfTokenThenSuccess() throws Exception {
    this.mockMvc.perform(post("/login").with(csrf())
        .accept(MediaType.TEXT_HTML)
        .param("username", "test")
        .param("password", "test"))
      .andDo(print())
      .andExpect(status().is3xxRedirection())
      .andExpect(header().string(HttpHeaders.LOCATION, "/login?error"));
  }

  @Test
  public void loginWhenValidCsrfTokenThenFail() throws Exception {
    this.mockMvc.perform(post("/login").with(csrf().useInvalidToken())
        .accept(MediaType.TEXT_HTML)
        .param("username", "test")
        .param("password", "test"))
      .andDo(print())
      .andExpect(status().isForbidden())
      ;
  }

  @Test
  @WithMockUser
  public void logoutWhenValidCsrfTokenThenSuccess() throws Exception {
    this.mockMvc.perform(post("/logout").with(csrf())
        .accept(MediaType.TEXT_HTML))
      .andExpect(status().is3xxRedirection())
      .andExpect(header().string(HttpHeaders.LOCATION, "/login?logout"));
  }
}