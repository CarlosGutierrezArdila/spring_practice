package com.example.quality_challenge.Exceptions;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity articleNotFoundHandler(ApiException e){
        return ResponseEntity.status(e.getStatusCode()).body(e.getDescription());
    }
}
