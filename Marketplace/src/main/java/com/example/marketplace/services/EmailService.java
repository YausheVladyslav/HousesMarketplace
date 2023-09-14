package com.example.marketplace.services;

import com.example.marketplace.entities.ActivationCodeEntity;
import com.example.marketplace.entities.UserEntity;
import com.example.marketplace.repositories.ActivateCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String SENDER = "i.vlad.yaushev@gmail.com";
    private static final String TEXT = "Code for activate account: ";
    private static final String SUBJECT = "Activate profile";
    //    private static final int CODE_LENGTH = 6;
    private final JavaMailSender javaMailSender;
    private final ActivateCodeRepository codeRepository;

    @Transactional
    public void sendCode(UserEntity user) {
        SimpleMailMessage message = new SimpleMailMessage();
        SecureRandom secureRandom = new SecureRandom();
        int randomCode = secureRandom.nextInt();
//        String newCode = RandomStringUtils.random(CODE_LENGTH, true, true);

        Optional<ActivationCodeEntity> userCode = codeRepository.findByUserEmail(user.getEmail());
        if (userCode.isPresent()) {
            userCode.get().setCode(randomCode);
            codeRepository.save(userCode.get());
        } else {
            ActivationCodeEntity activateCode = new ActivationCodeEntity();
            activateCode.setCode(randomCode);
            activateCode.setUserEmail(user.getEmail());
            codeRepository.save(activateCode);
        }

        message.setFrom(SENDER);
        message.setSubject(SUBJECT);
        message.setTo(user.getEmail());
        message.setText(TEXT + randomCode);
        javaMailSender.send(message);
    }
}
