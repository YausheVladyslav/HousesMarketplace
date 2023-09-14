package com.example.marketplace.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class MyErrorResponse {

    private String message;
    private String url;
    private LocalDateTime time;

    public MyErrorResponse(String message, String url) {
        this.message = message;
        this.url = url;
        this.time = LocalDateTime.now();
    }
}
