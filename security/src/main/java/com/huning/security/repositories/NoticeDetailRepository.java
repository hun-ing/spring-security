package com.huning.security.repositories;

import com.huning.security.entities.NoticeDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDetailRepository extends JpaRepository<NoticeDetailEntity, Long> {

  List<NoticeDetailEntity> findByEmail(String username);
}
