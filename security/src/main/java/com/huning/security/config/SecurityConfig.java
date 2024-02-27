package com.huning.security.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests((authorize) -> {
      authorize.requestMatchers("/myAccount", "myBalance", "myLoans", "myCards").authenticated()
        .requestMatchers("/notices", "/contact", "/register").permitAll();
    }).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
    return http.build();
  }

  /*@Bean
  public InMemoryUserDetailsManager userDetailsService() {

    *//*
    * 인메모리 방법 1 (기본 PasswordEncoder 사용)
    * *//*

    *//*UserDetails admin = User.withDefaultPasswordEncoder()
      .username("admin")
      .password("12345")
      .authorities("admin")
      .build();

    UserDetails user = User.withDefaultPasswordEncoder()
      .username("user")
      .password("12345")
      .authorities("read")
      .build();

    return new InMemoryUserDetailsManager(admin, user);*//*

    *//*
    * 인메모리 방법 2 (사용자 정의 PasswordEncoder 사용)
    * *//*

    UserDetails admin = User.withUsername("admin")
      .password("12345")
      .authorities("admin")
      .build();

    UserDetails user = User.withUsername("user")
      .password("12345")
      .authorities("read")
      .build();

    return new InMemoryUserDetailsManager(admin, user);
  }*/

  /*@Bean
  public UserDetailsService userDetailsService(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }*/

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
