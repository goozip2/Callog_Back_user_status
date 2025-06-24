package com.callog.callog_user_status.dto.response;

import com.callog.callog_user_status.common.Gender;
import com.callog.callog_user_status.domain.UserStatus;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserProfileResponse {
    private Long   height;
    private Long   weight;
    private Long   age;
    private Gender gender;

    public static UserProfileResponse of(UserStatus e) {
        return UserProfileResponse.builder()
                .height(e.getHeight())
                .weight(e.getWeight())
                .age(e.getAge())
                .gender(e.getGender())
                .build();
    }
}
