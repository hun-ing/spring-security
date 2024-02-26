package com.huning.security.config;

import com.huning.security.authority.domain.AuthorityDomain;
import com.huning.security.authority.dto.AuthorityDTO;
import com.huning.security.authority.repository.AuthorityRepository;
import com.huning.security.uesr.domain.UserDomain;
import com.huning.security.uesr.dto.UserDTO;
import com.huning.security.uesr.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataLoader {

  private final UserRepository userRepository;
  private final AuthorityRepository authorityRepository;

  @PostConstruct
  public void init() {
    // 초기 데이터 생성 및 저장
    UserDTO user = UserDTO.builder().username("test").password("test").build();
    AuthorityDTO authority = AuthorityDTO.builder().username("test").authority("write").build();
    userRepository.save(UserDomain.of(user).toCreateEntity());
    authorityRepository.save(AuthorityDomain.of(authority).toCreateEntity());
  }
}
