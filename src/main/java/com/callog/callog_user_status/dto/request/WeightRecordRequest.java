package com.callog.callog_user_status.dto.request;

import com.callog.callog_user_status.domain.WeightInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WeightRecordRequest(
        @NotNull @Positive Long weight,
        @PositiveOrZero Long height
) {
    public WeightInfo toEntity(String userId, LocalDate date) {
        return WeightInfo.builder()
                .userId(userId)
                .weightDate(date)
                .weight(weight)
                .height(height)
                .build();
    }
}

