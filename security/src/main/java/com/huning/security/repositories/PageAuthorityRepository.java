package com.huning.security.repositories;

import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.PageAuthorityEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PageAuthorityRepository extends JpaRepository<PageAuthorityEntity, Long> {
}
