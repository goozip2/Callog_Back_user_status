package com.callog.callog_user_status.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "weight_info", uniqueConstraints = @UniqueConstraint(
        name="uq_user_date", columnNames = {"userId","weightDate"}))
public class WeightInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weightId;

    @Column(nullable = false, length = 100)
    private String userId;

    private LocalDate weightDate;
    private Long weight;
    private Long height;

    @CreationTimestamp
    private LocalDateTime createAt;
}
