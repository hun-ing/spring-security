package com.huning.security.customer.repository;

import com.huning.security.customer.entity.CustomerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  List<CustomerEntity> findByEmail(String username);
}
