package com.huning.security.controller;

import com.huning.security.customer.domain.CustomerDomain;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.customer.entity.CustomerEntity;
import com.huning.security.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final CustomerRepository customerRepository;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody CustomerDTO requestDTO) {
    HttpStatus status = HttpStatus.CREATED;
    String msg = null;

    try {
      CustomerEntity customer = customerRepository.save(CustomerDomain.of(requestDTO).toCreateEntity());
      if (customer.getId() > 0) {
        msg = "Given user details are successfully registered";
      }
    } catch (Exception ex) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
      msg = "An exception occured due to" + ex.getMessage();
    }

    return ResponseEntity.status(status).body(msg);
  }
}
