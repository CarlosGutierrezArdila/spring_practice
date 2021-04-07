package com.gutierrez_carlos.store.exceptions;

public class InsuficientStockException extends RuntimeException {
    public InsuficientStockException(String message) {
        super(message);
    }
}
