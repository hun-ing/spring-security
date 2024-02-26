package com.huning.security.uesr.controller;

import com.huning.security.uesr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;
}
