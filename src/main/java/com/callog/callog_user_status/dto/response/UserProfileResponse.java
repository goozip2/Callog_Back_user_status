package com.callog.callog_user_status.dto.response;

import com.callog.callog_user_status.common.Gender;
import com.callog.callog_user_status.domain.UserStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserProfileResponse(
        Long height,
        Long weight,
        Long age,
        Gender gender,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserProfileResponse of(UserStatus s) {
        return new UserProfileResponse(
                s.getHeight(),
                s.getWeight(),
                s.getAge(),
                s.getGender(),
                s.getCreateAt(),
                s.getUpdatedAt()
        );
    }
}
