package com.callog.callog_user_status.event.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeightReminderEvent {
    public static final String TOPIC = "user.weight.reminder";

    private String eventId;        // UUID

    @NotBlank
    private Long userId;

    private LocalDate targetDate;

    public static WeightReminderEvent of(Long userId, LocalDate date) {
        return WeightReminderEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .userId(userId)
                .targetDate(date)
                .build();
    }
}
