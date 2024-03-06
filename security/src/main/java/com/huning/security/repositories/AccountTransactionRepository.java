package com.huning.security.repositories;

import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.CustomerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountTransactionRepository extends JpaRepository<AccountTransactionEntity, Long> {

  @Query(value = "select at from AccountTransactionEntity at where at.customer.customerId = :customerId")
  List<AccountTransactionEntity> findByCustomerIdOrderByTransactionDtDesc(Long customerId);
}
