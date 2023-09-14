package com.example.marketplace.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthUserRequest {

    @Email(message = "Invalid email")
    String email;
    @NotBlank
    @Size(min = 8, max = 64, message = "Password should be at least 8 symbols")
    String password;
}
