package com.example.quality_challenge.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String error;
    private String message;
    private Integer status;


    public ApiError(ApiException e) {
        error=e.getCode();
        message=e.getDescription();
        status=e.getStatusCode();
    }
}
