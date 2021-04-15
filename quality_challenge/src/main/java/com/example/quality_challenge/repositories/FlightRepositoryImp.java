package com.example.quality_challenge.repositories;

import com.example.quality_challenge.dto.FlightDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepositoryImp implements FlightRepository{

    public List<FlightDTO> loadDatabase(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<FlightDTO> flights = new ArrayList<>();
        try {
            flights = objectMapper.readValue(new File("src/main/resources/dbFlights.json"), new TypeReference<List<FlightDTO>>() {
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public List<FlightDTO> getFlights() {
        return loadDatabase();
    }


}
