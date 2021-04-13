package com.starwars.app.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.app.dto.CharacterDTO;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterRepositoryImp implements CharacterRepository {
    @Override
    public List<CharacterDTO> findCharacterByName(String name) {
        List<CharacterDTO> characters = null;
        characters = loadDataBase();
        List<CharacterDTO> result = characters.stream()
                .filter(CharacterDTO -> CharacterDTO.getName().contains(name)).collect(Collectors.toList());
        return characters;
    }

    private List<CharacterDTO> loadDataBase() {
        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:starwars.json");

        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<CharacterDTO>> typeRef = new TypeReference<>() {
        };

        List<CharacterDTO> characters = null;

        try {
            characters = objectMapper.readValue(file, typeRef);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return characters;
    }


}
