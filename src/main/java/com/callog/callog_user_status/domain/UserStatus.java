package com.callog.callog_user_status.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statsId;

    @Column(nullable = false, length = 100)
    private String userId;

    private Long height;
    private Long weight;
    private Long age;

    public enum Gender { MALE, FEMALE }
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime createAt;  // 최초 입력 일시

    @CreationTimestamp
    private LocalDateTime updatedAt;  // 마지막 수정 일시

}
