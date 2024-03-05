package com.huning.security.repositories;

import com.huning.security.entities.AccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
