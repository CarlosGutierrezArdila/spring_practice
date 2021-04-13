package com.calories.app.exeptions;

public class IngredientNotFoundException extends Exception {
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
