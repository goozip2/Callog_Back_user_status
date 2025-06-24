package com.callog.callog_user_status.service;

import com.callog.callog_user_status.domain.WeightInfo;
import com.callog.callog_user_status.dto.request.WeightRecordRequest;
import com.callog.callog_user_status.dto.response.WeightResponse;
//import com.callog.callog_user_status.event.WeightEvent;
import com.callog.callog_user_status.repository.UserStatusRepository;
import com.callog.callog_user_status.repository.WeightInfoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.callog.callog_user_status.domain.UserStatus;
//import com.callog.callog_user_status.event.producer.WeightEventProducer;
import com.callog.callog_user_status.dto.request.UserProfileRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service @RequiredArgsConstructor
public class WeightService {
    private final WeightInfoRepository repo;
    private final UserStatusRepository profileRepo;
    //private final WeightEventProducer producer;

    // 하루 몸무게 기록 – 존재하면 업데이트, 없으면 신규 INSERT
    @Transactional
    public WeightResponse record(Long userId, @Valid WeightRecordRequest req) {
        LocalDate today = LocalDate.now();
        //log.debug("[Weight] record userId={} date={} payload={}", userId, today, req);

        WeightInfo entity = repo.findByUserIdAndWeightDate(userId, today)
                .orElseGet(() -> req.toEntity(userId, today));

        entity.setWeight(req.getWeight());
        if (req.getHeight() != null) {
            entity.setHeight(req.getHeight());
        }
        repo.save(entity);

        UserStatus profile = profileRepo.findByUserId(userId)
                .orElseGet(() ->               // 없으면 새로 만든다
                        UserStatus.builder()
                                .userId(userId)
                                .height(req.getHeight())
                                .weight(req.getWeight())
                                .build());

        profile.setWeight(req.getWeight());
        if (req.getHeight() != null) profile.setHeight(req.getHeight());
        profileRepo.save(profile);

        // Kafka 이벤트 발행
//        WeightEvent event = new WeightEvent(userId, today, req.getWeight(),
//                Optional.ofNullable(req.getHeight()).orElse(profile.getHeight()));
//        producer.publish(event);

        return WeightResponse.of(entity);
    }

    @Transactional(readOnly = true)
    public List<WeightResponse> latest(Long userId, int days) {
        if (days <= 0) days = 7;
        List<WeightInfo> list = repo.findTop7ByUserIdOrderByWeightDateDesc(userId);
        return list.stream().map(WeightResponse::of).toList();
    }
}
