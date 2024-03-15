package com.huning.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

record CustomerAuthenticationTokenDTO (String email, String password) {};

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/user", "POST");
  private boolean postOnly = true;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public CustomAuthenticationFilter() {
    super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
  }

  public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
  }

  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    throws AuthenticationException, IOException {
    if (this.postOnly && !request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    } else {

      CustomerAuthenticationTokenDTO authenticationTokenDTO = objectMapper.readValue(request.getReader(), CustomerAuthenticationTokenDTO.class);
      String username = authenticationTokenDTO.email();
      username = username != null ? username.trim() : "";
      String password = authenticationTokenDTO.password();
      password = password != null ? password : "";
      UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
      this.setDetails(request, authRequest);
      return this.getAuthenticationManager().authenticate(authRequest);
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class AccountDTO {
    private String email;
    private String pwd;
  }

  protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
    authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
  }

  public void setPostOnly(boolean postOnly) {
    this.postOnly = postOnly;
  }


}
