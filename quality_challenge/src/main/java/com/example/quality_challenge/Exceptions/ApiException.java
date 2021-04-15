package com.example.quality_challenge.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private final String code;
    private final String description;
    private final Integer statusCode;
}
