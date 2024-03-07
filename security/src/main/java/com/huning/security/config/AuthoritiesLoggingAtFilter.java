package com.huning.security.config;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthoritiesLoggingAtFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    log.info("Authentication Validation is in progress");
    chain.doFilter(request, response);
  }

}