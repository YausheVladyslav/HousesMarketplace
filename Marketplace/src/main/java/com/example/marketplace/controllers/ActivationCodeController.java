package com.example.marketplace.controllers;

import com.example.marketplace.entities.UserEntity;
import com.example.marketplace.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActivationCodeController {

    private final EmailService emailService;

    @GetMapping("/send-code")
    public ResponseEntity<Void> sendCode(@AuthenticationPrincipal UserEntity user){
        emailService.sendCode(user);
        return ResponseEntity.ok().build();
    }
}
