package com.caffeinedoctor.userservice.scheduler;

import com.caffeinedoctor.userservice.entitiy.Refresh;
import com.caffeinedoctor.userservice.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenCleanupScheduler {

    private final RefreshRepository refreshRepository;

    // 매일 자정에 실행되는 스케줄러, 한국 시간대(KST)를 명시
//    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
//    public void cleanupExpiredTokens() {
//        // 한국 시간대를 기준으로 현재 시간을 계산
//        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//        List<Refresh> expiredTokens = refreshRepository.findByExpirationBefore(now);
//        refreshRepository.deleteAll(expiredTokens);
//    }

}
