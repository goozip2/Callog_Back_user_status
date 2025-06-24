package com.callog.callog_user_status.repository;

import com.callog.callog_user_status.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    Optional<UserStatus> findByUserId(Long userId);

    @Query("select u.userId from UserStatus u")
    List<Long> findAllUserIds();
}
