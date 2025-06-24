package com.callog.callog_user_status.dto.request;

import com.callog.callog_user_status.domain.WeightInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class WeightRecordRequest {
    @NotNull @Positive private Long weight;
    @Positive private Long height;           // null 허용 → 키 변경 없이 몸무게만 기록할 때

    public WeightInfo toEntity(String userId, LocalDate date) {
        return WeightInfo.builder()
                .userId(userId)
                .weightDate(date)
                .weight(weight)
                .height(height)
                .build();
    }
}

