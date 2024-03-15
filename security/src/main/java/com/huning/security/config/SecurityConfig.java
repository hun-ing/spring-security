package com.huning.security.config;

import com.huning.security.constants.SecurityConstants;
import com.huning.security.filters.AjaxAuthenticationFailureHandler;
import com.huning.security.filters.AjaxAuthenticationSuccessHandler;
import com.huning.security.filters.JWTTokenGeneratorFilter;
import com.huning.security.filters.JWTTokenValidatorFilter;
import com.huning.security.filters.RequestValidationBeforeFilter;
import com.huning.security.pages.service.PageService;
import com.huning.security.repositories.CustomerRepository;
import com.huning.security.repositories.PageRepository;
import java.security.AuthProvider;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final PageService pageService;
  private final AuthenticationConfiguration authenticationConfiguration;


  @Value("${spring.security.debug:true}")
  boolean webSecurityDebug;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.debug(webSecurityDebug);
  }

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
    AuthorizationManager<RequestAuthorizationContext> authz) throws Exception {
    http
//      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .csrf(AbstractHttpConfigurer::disable)
//      .csrf((csrf) -> csrf
//        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
//        .ignoringRequestMatchers("/contact", "/register")
//      )
//      .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
      .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
      .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
//      .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
//      .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
      .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
      .authorizeHttpRequests((authorize) ->
          authorize

//          .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
//          .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT", "VIEWBALANCE")
//          .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
//          .requestMatchers("/myCards").hasAuthority("VIEWCARDS")

//          .requestMatchers("/myAccount").hasRole("USER")
//          .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
//          .requestMatchers("/myLoans").hasRole("USER")
//          .requestMatchers("/myCards").hasRole("USER")
////          .requestMatchers("/notices").hasRole("ADMIN")
//          .requestMatchers("/", "/user").authenticated()
//          .requestMatchers( "/contact", "/register", "/notices").permitAll()

            .anyRequest().access(authz)
      )
      .formLogin(AbstractHttpConfigurer::disable)
      .httpBasic(AbstractHttpConfigurer::disable)
//      .formLogin(Customizer.withDefaults())
//      .httpBasic(Customizer.withDefaults())
    ;

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setExposedHeaders(List.of(SecurityConstants.JWT_HEADER));
    configuration.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CustomAuthorizationManager customAuthorizationManager()
    throws Exception {
    return new CustomAuthorizationManager(authorizationManagerFactoryBean().getObject());
  }

  @Bean
  public AuthorizationManagerFactoryBean authorizationManagerFactoryBean() {
    AuthorizationManagerFactoryBean authorizationManagerFactoryBean = new AuthorizationManagerFactoryBean();
    authorizationManagerFactoryBean.setPageService(pageService);
    return authorizationManagerFactoryBean;
  }

  public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
    CustomAuthenticationFilter ajaxLoginProcessingFilter = new CustomAuthenticationFilter();
    ajaxLoginProcessingFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager()); // 등록
    ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
    ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
    return ajaxLoginProcessingFilter;
  }

  @Bean
  public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
    return new AjaxAuthenticationSuccessHandler();
  }

  @Bean
  public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
    return new AjaxAuthenticationFailureHandler();
  }

  @Bean
  public AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthProvider, CustomAuthenticationProvider1 customAuthProvider1) {
    return new ProviderManager(Arrays.asList(customAuthProvider, customAuthProvider1), null);
  }
}
