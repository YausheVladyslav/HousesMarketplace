package com.example.marketplace.services;

import com.example.marketplace.entities.ActivationCodeEntity;
import com.example.marketplace.repositories.ActivateCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ActivationCodeService {

    private static final int VALID_CODE_HOURS = 24;
    private final ActivateCodeRepository codeRepository;

//  cron for every 24 hours: "0 0 0/24 ? * * *" || "0 0 */24 ? * *"
//  cron for every minute: "0 * * ? * *"
//  cron for every second: "* * * ? * *"

    @Scheduled(cron = "0 0 */24 ? * *")
    private void cleanCodeList() {
//        System.out.println("SCHEDULED");
        LocalDateTime date = LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
        List<ActivationCodeEntity> allCodes = codeRepository.findAll();
        for (ActivationCodeEntity code : allCodes) {
            long timeAgo = ChronoUnit.MINUTES.between(code.getCreatedOn(), date);
            if (timeAgo >= VALID_CODE_HOURS) {
                codeRepository.delete(code);
            }
        }
    }
}
