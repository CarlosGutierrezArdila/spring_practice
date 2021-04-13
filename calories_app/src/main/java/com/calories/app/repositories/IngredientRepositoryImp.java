package com.calories.app.repositories;

import com.calories.app.entities.IngredientInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryImp implements IngredientRepository {
    @Override
    public List<IngredientInfo> getIngredients() {
        List<IngredientInfo> ingredients = null;

        ingredients = loadDataBase();

        return ingredients;
    }

    private List<IngredientInfo> loadDataBase() {
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:food.json");

        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<IngredientInfo>> typeRef = new TypeReference<>() {
        };

        List<IngredientInfo> ingredients = null;

        try {
            ingredients = objectMapper.readValue(file, typeRef);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
