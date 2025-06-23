package com.callog.callog_user_status.domain;

import com.callog.callog_user_status.common.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Table(name = "user_stats", uniqueConstraints = @UniqueConstraint(name = "uq_user", columnNames = "userId"))
public class UserStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statsId;

    @Column(nullable = false, length = 100)
    private String userId;

    private Long height;
    private Long weight;
    private Long age;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime createAt;  // 최초 입력 일시

    @UpdateTimestamp
    private LocalDateTime updatedAt;  // 마지막 수정 일시

}
