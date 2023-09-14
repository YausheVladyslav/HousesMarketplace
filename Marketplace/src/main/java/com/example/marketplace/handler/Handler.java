package com.example.marketplace.handler;

import com.example.marketplace.exceptions.BadRequestException;
import com.example.marketplace.responses.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class Handler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<MyErrorResponse> jwtCheck(
//            Exception exception,
//            ServletWebRequest request
//    ) {
//        MyErrorResponse errorResponse = new MyErrorResponse(exception.getMessage(),
//                request.getRequest().getRequestURI());
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomErrorResponse> requestHandler(
            BadRequestException exception,
            ServletWebRequest request
    ) {

        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequest().getRequestURI()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
