package com.callog.callog_user_status.service;

import com.callog.callog_user_status.domain.UserStatus;
import com.callog.callog_user_status.dto.request.UserProfileRequest;
import com.callog.callog_user_status.dto.response.UserProfileResponse;
import com.callog.callog_user_status.common.exception.StatNotFoundException;
import com.callog.callog_user_status.repository.UserStatusRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserStatusService {
    private final UserStatusRepository repo;

    @Transactional
    public UserProfileResponse upsert(String userId, @Valid UserProfileRequest req) {
        log.debug("[UserStatus] upsert userId={} payload={}", userId, req);

        UserStatus entity = repo.findByUserId(userId).orElseGet(() -> req.toEntity(userId));
        entity.setHeight(req.height());
        entity.setWeight(req.weight());
        entity.setAge(req.age());
        entity.setGender(req.gender());

        repo.save(entity);
        return UserProfileResponse.of(entity);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse get(String userId) {
        UserStatus entity = repo.findByUserId(userId)
                .orElseThrow(() -> new StatNotFoundException("User profile not found: " + userId));
        return UserProfileResponse.of(entity);
    }

    @Transactional
    public UserProfileResponse patchHeightWeight(String userId, Long height, Long weight) {
        UserStatus entity = repo.findByUserId(userId)
                .orElseThrow(() -> new StatNotFoundException("User profile not found: " + userId));
        if (height != null) entity.setHeight(height);
        if (weight != null) entity.setWeight(weight);
        return UserProfileResponse.of(entity);
    }
}