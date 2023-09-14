package com.example.marketplace.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SaveUserRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;
}
