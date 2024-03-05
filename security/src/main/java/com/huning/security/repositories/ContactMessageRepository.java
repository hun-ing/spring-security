package com.huning.security.repositories;

import com.huning.security.entities.ContactMessageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessageEntity, Long> {

}
