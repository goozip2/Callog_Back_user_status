package com.callog.callog_user_status.dto.request;

import com.callog.callog_user_status.common.Gender;
import com.callog.callog_user_status.domain.UserStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Builder
public record UserProfileRequest(
        @NotNull @Positive(message = "height must be > 0")        Long height,
        @NotNull @Positive(message = "weight must be > 0")        Long weight,
        @NotNull @Positive(message = "age must be > 0")           Long age,
        @NotNull                                                   Gender gender
) {

    public UserStatus toEntity(String userId) {
        return UserStatus.builder()
                .userId(userId)
                .height(height)
                .weight(weight)
                .age(age)
                .gender(gender)
                .build();
    }
}
