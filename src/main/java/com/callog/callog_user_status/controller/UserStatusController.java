package com.callog.callog_user_status.controller;

import com.callog.callog_user_status.common.dto.ApiResponseDto;
import com.callog.callog_user_status.dto.request.UserProfileRequest;
import com.callog.callog_user_status.dto.response.UserProfileResponse;
import com.callog.callog_user_status.service.UserStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class UserStatusController {

    private final UserStatusService statusSvc;

    // 로그인 시 사용하는 backend API
    @GetMapping("/backend/userStatus/login/{userId}")
    public UserProfileResponse getUserStats(@PathVariable Long userId) {
        log.info("[user 로그인]: height, weight, age, gender 전달");
        return statusSvc.get(userId);
    }

    // 회원가입 시 사용하는 backend API
    @PostMapping("/backend/userStatus/register")
    public UserProfileResponse upsertProfile(
//            @RequestHeader("X-Auth-User-Id") Long userId,
            @RequestParam Long userId,
            @Valid @RequestBody UserProfileRequest req) {
        log.info("[user 회원가입]: height, weight, age, gender 저장");
        return statusSvc.upsert(userId, req);
    }

    // 프로필 조회
    @GetMapping("/userStatus")
    public ApiResponseDto<UserProfileResponse> getProfile(@RequestHeader("X-Auth-User-Id") Long userId) {
        return ApiResponseDto.createOk(statusSvc.get(userId));
    }
}

