package com.huning.security.customer.controller;

import com.huning.security.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerRepository customerRepository;
}
