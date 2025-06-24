package com.callog.callog_user_status.repository;

import com.callog.callog_user_status.domain.WeightInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeightInfoRepository extends JpaRepository<WeightInfo, Long> {
    List<WeightInfo> findTop7ByUserIdOrderByWeightDateDesc(Long userId);
    Optional<WeightInfo> findByUserIdAndWeightDate(Long userId, LocalDate date);
}
