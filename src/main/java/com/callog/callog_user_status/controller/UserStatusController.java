package com.callog.callog_user_status.controller;

import com.callog.callog_user_status.common.dto.ApiResponseDto;
import com.callog.callog_user_status.dto.request.UserProfileRequest;
import com.callog.callog_user_status.dto.response.UserProfileResponse;
import com.callog.callog_user_status.service.UserStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class UserStatusController {

    private final UserStatusService statusSvc;

    // 로그인 시 사용하는 backend API
    @GetMapping("/backend/userStatus/login/{userId}")
    public UserProfileResponse getUserStats(@PathVariable Long userId) {
        return statusSvc.get(userId);
    }

    // 회원가입 시 사용하는 backend API
    @PostMapping("/backend/userStatus/register")
    public UserProfileResponse upsertProfile(
//            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam Long userId,
            @Valid @RequestBody UserProfileRequest req) {
        return statusSvc.upsert(userId, req);
    }

    // 프로필 조회
    @GetMapping("/userStatus/{userId}")
    public ApiResponseDto<UserProfileResponse> getProfile(@RequestHeader("X-USER-ID") Long userId) {
        return ApiResponseDto.createOk(statusSvc.get(userId));
    }
}

