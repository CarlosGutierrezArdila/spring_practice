package com.calories.app.services;

import com.calories.app.dto.DishDTO;
import com.calories.app.dto.IngredientDTO;
import com.calories.app.dto.ResponseDTO;
import com.calories.app.entities.IngredientInfo;
import com.calories.app.exeptions.ErrorDTO;
import com.calories.app.exeptions.IngredientNotFoundException;
import com.calories.app.repositories.IngredientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.SchedulingAwareRunnable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Data
public class IngredienServiceImp implements IngredientService {
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredienServiceImp(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Object calculateCalories(DishDTO inputDish) {
        try {
            HashMap<IngredientDTO, Double> calculatedList = calculateIngredientList(inputDish.getIngredients());
            IngredientDTO highestIngredient = highestCaloriesIngredient(calculatedList);
            Double calorieCount = totalCalories(calculatedList);
            return new ResponseDTO(calorieCount, calculatedList, highestIngredient);
        } catch (IngredientNotFoundException e) {
            e.printStackTrace();
            return new ErrorDTO("Ingredient nor found", "ingredient " + e.getMessage() + " not found");
        }

    }


    private HashMap<IngredientDTO, Double> calculateIngredientList(List<IngredientDTO> ingredients) throws IngredientNotFoundException {
        List<IngredientInfo> caloriesList = ingredientRepo.getIngredients();
        HashMap<IngredientDTO, Double> result = new HashMap<>();
        for (IngredientDTO ingredient : ingredients) {
            IngredientInfo match = caloriesList
                    .stream()
                    .filter(ingredientInfo -> ingredientInfo.getName().equals(ingredient.getName()))
                    .findAny()
                    .orElse(null);
            if (match == null) throw new IngredientNotFoundException(ingredient.getName());
            result.put(ingredient, ingredient.getWeight() * match.getCalories());
        }
        return result;
    }

    private IngredientDTO highestCaloriesIngredient(HashMap<IngredientDTO, Double> ingredientList) {
        IngredientDTO max = new IngredientDTO("", 0.0);
        Double maxCal = 0.0;
        for (Map.Entry<IngredientDTO, Double> entry : ingredientList.entrySet()) {
            if (entry.getValue() > maxCal) {
                max = entry.getKey();
                maxCal = entry.getValue();
            }
        }
        return max;
    }

    private Double totalCalories(HashMap<IngredientDTO, Double> ingredientList) {
        Double count = 0.0;
        for (Map.Entry<IngredientDTO, Double> entry : ingredientList.entrySet()) {
            count += entry.getValue();
        }
        return count;
    }


}
