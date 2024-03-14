package com.huning.security.config;

import com.huning.security.entities.AuthorityEntity;
import com.huning.security.entities.CustomerEntity;
import com.huning.security.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;
  private static final String ROLE_PREFIX ="ROLE_";

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String pwd = authentication.getCredentials().toString();
    List<CustomerEntity> customer = customerRepository.findByEmail(username);

    if (customer.isEmpty()) {
      throw new BadCredentialsException("No user registered with this details!");
    }

    if (!passwordEncoder.matches(pwd, customer.getFirst().getPwd())) {
      throw new BadCredentialsException("Invalid password!");
    }

    return new UsernamePasswordAuthenticationToken(username, pwd,
      getGrantedAuthorities(customer.getFirst().getAuthority()));
  }

  private List<GrantedAuthority> getGrantedAuthorities(AuthorityEntity authority) {
    return List.of(new SimpleGrantedAuthority(ROLE_PREFIX + authority.getName()));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
