package com.calories.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DishDTO {
    private String name;
    private List<IngredientDTO> ingredients;
}
