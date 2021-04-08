package com.gutierrez_carlos.store.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ArticleNotFoundException extends RuntimeException{
    HttpStatus status;
    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
