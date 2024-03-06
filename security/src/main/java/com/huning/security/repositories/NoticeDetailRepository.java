package com.huning.security.repositories;

import com.huning.security.entities.NoticeDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeDetailRepository extends JpaRepository<NoticeDetailEntity, Long> {

  @Query(value = "select n from NoticeDetailEntity n")
  List<NoticeDetailEntity> findAllActiveNotices();
}
