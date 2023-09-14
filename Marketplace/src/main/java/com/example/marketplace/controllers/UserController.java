package com.example.marketplace.controllers;

import com.example.marketplace.entities.UserEntity;
import com.example.marketplace.requests.activate_code.ActivateCodeRequest;
import com.example.marketplace.requests.user.AuthUserRequest;
import com.example.marketplace.requests.user.SaveUserRequest;
import com.example.marketplace.responses.AuthenticationResponse;
import com.example.marketplace.services.JwtService;
import com.example.marketplace.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@RequestBody SaveUserRequest request) {
        userService.saveUser(
                request.getFirstName(),
                request.getSecondName(),
                request.getEmail(),
                request.getPassword(),
                request.getRepeatPassword());
        return ResponseEntity.ok("User saved");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthUserRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(jwtService.createToken(
                userService.login(request.getEmail(), request.getPassword())));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("successful test");
    }

    @PutMapping("/activate")
    public ResponseEntity<Void> activateProfile(
            @RequestBody ActivateCodeRequest request,
            @AuthenticationPrincipal UserEntity user) {
        userService.activateProfile(user, request.getActivateCode());
        return ResponseEntity.ok().build();
    }

}
