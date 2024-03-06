package com.huning.security.repositories;

import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.CustomerEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

  Optional<AccountEntity> findByCustomer(CustomerEntity customerEntity);
  Optional<AccountEntity> findByAccountNumber(Long accountNumber);
}
