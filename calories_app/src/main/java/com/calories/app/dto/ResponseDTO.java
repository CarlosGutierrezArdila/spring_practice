package com.calories.app.dto;

import com.calories.app.entities.IngredientInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private Double totalCaloriesAmount;
    private HashMap<IngredientDTO, Double> ingredientCaloriesAmount;
    private IngredientDTO highestCaloriesIngredient;

}
