package com.callog.callog_user_status.service;

import com.callog.callog_user_status.domain.UserStatus;
import com.callog.callog_user_status.dto.request.UserProfileRequest;
import com.callog.callog_user_status.dto.response.UserProfileResponse;
import com.callog.callog_user_status.repository.UserStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserStatusService {
    private final UserStatusRepository userStatusRepository;

//    @Transactional
//    public UserProfileResponse createOrInit(String userId, UserProfileRequest req){
//        UserStatus stats = userStatusRepository.findByUserId(userId).orElse(null);
//
//
//    }


}
