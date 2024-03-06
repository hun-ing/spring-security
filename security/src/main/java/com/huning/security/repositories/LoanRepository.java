package com.huning.security.repositories;

import com.huning.security.entities.CustomerEntity;
import com.huning.security.entities.LoanEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

  List<LoanEntity> findByCustomerOrderByStartDtDesc(CustomerEntity customer);

  @Query(value = "select l from LoanEntity l where l.customer.customerId = : customerId")
  List<LoanEntity> findAllByCustomerIdOrderByStartDtDesc(Long customerId);
}
