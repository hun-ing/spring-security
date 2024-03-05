package com.huning.security.repositories;

import com.huning.security.entities.LoanEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

}
