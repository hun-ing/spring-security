package com.huning.security.repositories;

import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.CardEntity;
import com.huning.security.entities.CustomerEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

  Optional<CardEntity> findByCustomer(CustomerEntity customerEntity);

  @Query(value = "select c from CardEntity c where c.customer.customerId = :customerId")
  List<CardEntity> findAllByCustomerId(Long customerId);
}
