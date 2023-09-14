package com.example.marketplace.responses;

import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
public class CustomErrorResponse {

    private HttpStatus status;
    private String message;
    private String url;
    private LocalDateTime date;

    public CustomErrorResponse(HttpStatus status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
        this.date = LocalDateTime.now();
    }
}
