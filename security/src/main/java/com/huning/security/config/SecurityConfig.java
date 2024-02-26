package com.huning.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

    /*
     * 사용자 설정
     * */

    http.authorizeHttpRequests((authorize) -> {
      authorize.requestMatchers("/myAccount", "myBalance", "myLoans", "myCards").authenticated()
        .requestMatchers("notices", "contact").permitAll();
    }).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
    return http.build();

    /*
     * 모든 요청을 거부하는 설정
     * */
    
    /*http.authorizeHttpRequests((authorize) -> {
      authorize.anyRequest().denyAll();
    }).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
    return http.build();*/

    /*
     * 모든 요청을 허용하는 설정
     * */

    /*http.authorizeHttpRequests((authorize) -> {
      authorize.anyRequest().permitAll();
    }).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
    return http.build();*/
  }
}
