package com.callog.callog_user_status.event.scheduler;

import com.callog.callog_user_status.event.domain.WeightReminderEvent;
import com.callog.callog_user_status.event.producer.KafkaMessageProducer;
import com.callog.callog_user_status.repository.UserStatusRepository;
import com.callog.callog_user_status.repository.WeightInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class WeightReminderScheduler {

    private final UserStatusRepository userRepo;
    private final WeightInfoRepository weightRepo;
    private final KafkaMessageProducer producer;

    // 매일 정오 몸무게 입력 알림 받기
    @Scheduled(cron = "0 0 12 * * *", zone = "Asia/Seoul")
    public void fireDailyReminders() {

        LocalDate today = LocalDate.now();                // 오늘 날짜
        long sent = userRepo.findAllUserIds().stream()    // 모든 회원
                .filter(id -> weightRepo                      // 오늘 입력 안 한 회원만
                        .findByUserIdAndWeightDate(id, today)
                        .isEmpty())
                .peek(id -> producer.send(
                        WeightReminderEvent.TOPIC,
                        WeightReminderEvent.of(id, today)))   // 이벤트 발행
                .count();

        log.info("WeightReminder ▶ {} users notified for {}", sent, today);
    }



}
