package com.callog.callog_user_status.repository;

import com.callog.callog_user_status.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    Optional<UserStatus> findByUserId(String userId);
}
