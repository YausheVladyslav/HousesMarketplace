package com.example.marketplace.requests.penthouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PenthouseByTitleRequest {

    @NotBlank
    private String title;
}
