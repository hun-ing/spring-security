package com.huning.security.repositories;

import com.huning.security.entities.CustomerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

  List<CustomerEntity> findByEmail(String username);
}
