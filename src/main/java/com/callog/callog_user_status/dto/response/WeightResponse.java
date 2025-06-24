package com.callog.callog_user_status.dto.response;

import com.callog.callog_user_status.domain.WeightInfo;

import java.time.LocalDate;

public record WeightResponse(LocalDate weightDate, Long weight) {
    public static WeightResponse of(WeightInfo e) {
        return new WeightResponse(e.getWeightDate(), e.getWeight());
    }
}
