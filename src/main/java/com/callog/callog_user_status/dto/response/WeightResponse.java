package com.callog.callog_user_status.dto.response;

import com.callog.callog_user_status.domain.WeightInfo;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class WeightResponse {
    private LocalDate weightDate;
    private Long      weight;

    public static WeightResponse of(WeightInfo e) {
        return WeightResponse.builder()
                .weightDate(e.getWeightDate())
                .weight(e.getWeight())
                .build();
    }
}
