package com.huning.security.repositories;

import com.huning.security.entities.AccountTransactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransactionEntity, Long> {

}
