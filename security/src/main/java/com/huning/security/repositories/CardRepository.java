package com.huning.security.repositories;

import com.huning.security.entities.CardEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

  List<CardEntity> findByEmail(String username);
}
