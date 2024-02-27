package com.huning.security.config;

import com.huning.security.customer.entity.CustomerEntity;
import com.huning.security.customer.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

  private final CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<GrantedAuthority> authorities = new ArrayList<>();
    List<CustomerEntity> customer = customerRepository.findByEmail(username);

    if (customer.isEmpty()) {
      throw new UsernameNotFoundException("User details not found for the user:" + username);
    }

    String userName = customer.getFirst().getEmail();
    String password = customer.getFirst().getPwd();
    authorities.add(new SimpleGrantedAuthority(customer.getFirst().getRole()));

    return new User(userName, password, authorities);
  }
}
