package com.callog.callog_user_status.service;

import com.callog.callog_user_status.domain.WeightInfo;
import com.callog.callog_user_status.dto.request.WeightRecordRequest;
import com.callog.callog_user_status.dto.response.WeightResponse;
import com.callog.callog_user_status.repository.UserStatusRepository;
import com.callog.callog_user_status.repository.WeightInfoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.callog.callog_user_status.domain.UserStatus;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service @RequiredArgsConstructor
public class WeightService {
    private final WeightInfoRepository repo;
    private final UserStatusRepository profileRepo;
    // private final WeightEventProducer producer;

    // 하루 몸무게 기록 – 존재하면 업데이트, 없으면 신규 INSERT
    @Transactional
    public WeightResponse record(String userId, @Valid WeightRecordRequest req) {
        LocalDate today = LocalDate.now();
        log.debug("[Weight] record userId={} date={} payload={}", userId, today, req);

        WeightInfo entity = repo.findByUserIdAndWeightDate(userId, today)
                .orElseGet(() -> req.toEntity(userId, today));

        entity.setWeight(req.weight());
        if (req.height() != null) {
            entity.setHeight(req.height());
        }
        repo.save(entity);

        UserStatus profile = profileRepo.findByUserId(userId)
                .orElseGet(() ->               // 없으면 새로 만든다
                        UserStatus.builder()
                                .userId(userId)
                                .height(req.height())
                                .weight(req.weight())
                                .build());

        profile.setWeight(req.weight());
        if (req.height() != null) profile.setHeight(req.height());
        profileRepo.save(profile);

        // producer.publish(new WeightRecordedEvent(userId, today, req.weight(), req.height()));

        return WeightResponse.of(entity);
    }

    @Transactional(readOnly = true)
    public List<WeightResponse> latest(String userId, int days) {
        if (days <= 0) days = 7;
        List<WeightInfo> list = repo.findTop7ByUserIdOrderByWeightDateDesc(userId);
        return list.stream().map(WeightResponse::of).toList();
    }
}
