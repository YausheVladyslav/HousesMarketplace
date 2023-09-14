package com.example.marketplace.requests.activate_code;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ActivateCodeRequest {

    @NotBlank
    private int activateCode;
}
