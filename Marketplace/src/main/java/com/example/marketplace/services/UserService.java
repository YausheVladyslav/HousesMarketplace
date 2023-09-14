package com.example.marketplace.services;

import com.example.marketplace.entities.ActivationCodeEntity;
import com.example.marketplace.entities.Role;
import com.example.marketplace.entities.UserEntity;
import com.example.marketplace.entities.penthouse.PenthouseEntity;
import com.example.marketplace.exceptions.BadRequestException;
import com.example.marketplace.repositories.ActivateCodeRepository;
import com.example.marketplace.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ActivateCodeRepository codeRepository;
    private final EmailService emailService;

    @Transactional
    public void saveUser(
            String firstName,
            String secondName,
            String email,
            String password,
            String repeatPassword) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("This email is already taken");
        } else if (!password.equals(repeatPassword)) {
            throw new RuntimeException("Invalid password");
        }
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setRole(Role.ROLE_INACTIVE_USER);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        emailService.sendCode(user);
    }

    public UserEntity login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return user;
    }

    public void activateProfile(UserEntity user, int code) {
        ActivationCodeEntity userCode = codeRepository.findByUserEmail(user.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid code"));
        if (userCode.getCode() == code) {
            user.setRole(Role.ROLE_ACTIVE_USER);
            userRepository.save(user);
        }
    }

}
