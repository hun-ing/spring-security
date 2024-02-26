package com.huning.security.uesr.repository;

import com.huning.security.uesr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
