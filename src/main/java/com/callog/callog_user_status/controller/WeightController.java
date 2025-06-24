package com.callog.callog_user_status.controller;

import com.callog.callog_user_status.common.dto.ApiResponseDto;
import com.callog.callog_user_status.dto.request.WeightRecordRequest;
import com.callog.callog_user_status.dto.response.WeightResponse;
import com.callog.callog_user_status.service.WeightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class WeightController {

    private final WeightService weightSvc;

    // 키·몸무게만 부분 수정
    @PostMapping("/userStatus/{userId}")
    public ApiResponseDto<WeightResponse> recordWeight(
            @RequestHeader("X-USER-ID") Long userId,
            @Valid @RequestBody WeightRecordRequest req) {
        return ApiResponseDto.createOk(weightSvc.record(userId, req));
    }

    // 최근 7일 몸무게 리스트
    @GetMapping("/userStatus/weights")
    public ApiResponseDto<List<WeightResponse>> latestWeights(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam(defaultValue = "7") int days) {
        return ApiResponseDto.createOk(weightSvc.latest(userId, days));
    }
}

