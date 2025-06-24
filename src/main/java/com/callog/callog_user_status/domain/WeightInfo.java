package com.callog.callog_user_status.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "weight_info", uniqueConstraints = @UniqueConstraint(
        name="uq_user_date", columnNames = {"userId","weightDate"}))
public class WeightInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weightId;

    @Column(nullable = false)
    private Long userId;

    private LocalDate weightDate;
    private Long weight;
    private Long height;

    @CreationTimestamp
    private LocalDateTime createAt;
}
