package com.calories.app.services;


import com.calories.app.dto.DishDTO;
import com.calories.app.dto.ResponseDTO;

public interface IngredientService {

    public ResponseDTO calculateCalories(DishDTO inputDish);

}
