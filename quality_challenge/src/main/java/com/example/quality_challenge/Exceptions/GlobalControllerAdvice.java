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
        return ResponseEntity.status(e.getStatusCode()).body(new ApiError(e));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity oops(RuntimeException e){
        return ResponseEntity.status(500).body(new ApiError("A000", "tenemos problemas para procesar la solicitud", 500));
    }
}
