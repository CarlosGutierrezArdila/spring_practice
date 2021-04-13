package com.starwars.app.repositories;

import com.starwars.app.dto.CharacterDTO;

import java.util.List;

public interface CharacterRepository {
    public List<CharacterDTO> findCharacterByName(String name);
}
