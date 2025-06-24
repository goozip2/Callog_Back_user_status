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
//@RequestMapping("/api/v1/userStatus")
public class UserStatusController {

    private final UserStatusService statusSvc;

    // 등록
    @PostMapping("/userStatus")
    public ApiResponseDto<UserProfileResponse> upsertProfile(
            @RequestHeader("X-USER-ID") String userId,
            @Valid @RequestBody UserProfileRequest req) {
        return ApiResponseDto.createOk(statusSvc.upsert(userId, req));
    }

    // 프로필 조회
    @GetMapping("/userStatus/{userId}")
    public ApiResponseDto<UserProfileResponse> getProfile(@RequestHeader("X-USER-ID") String userId) {
        return ApiResponseDto.createOk(statusSvc.get(userId));
    }
}

