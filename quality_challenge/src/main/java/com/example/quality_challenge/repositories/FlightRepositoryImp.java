package com.example.quality_challenge.repositories;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.dto.FlightDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Data
public class FlightRepositoryImp implements FlightRepository{

    private String filePath = "src/main/resources/dbFlights.json";
    private List<FlightDTO> flights;
    private List<String> destinationCities;
    private List<String> originCities;

    public FlightRepositoryImp() {
        flights = loadDatabase();
        destinationCities = flights.stream().map((flightDTO)-> flightDTO.getDestination() ).collect(Collectors.toList());
        originCities = flights.stream().map((flightDTO)-> flightDTO.getOrigin() ).collect(Collectors.toList());
    }

    public List<FlightDTO> loadDatabase(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<FlightDTO> flights = new ArrayList<>();
        try {
            flights = objectMapper.readValue(new File(filePath), new TypeReference<List<FlightDTO>>() {
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public List<FlightDTO> getFlights() {
        return flights;
    }

    public boolean destinationExists(String name) {
        return destinationCities.contains(name);
    }

    public boolean originExists(String name){
        return originCities.contains(name);
    }

    @Override
    public FlightDTO getFlightByID(String id, String origin, String destination) {
        List<FlightDTO> result =  flights.stream().filter(
                flightDTO -> flightDTO.getFlightNumber().equals(id) && flightDTO.getOrigin().toUpperCase().equals(origin.toUpperCase()) && flightDTO.getDestination().toUpperCase().equals(destination.toUpperCase())
        ).collect(Collectors.toList());
        if(result.size()==0)
            throw new ApiException("F006", "No flight matching code origin and destination provided exists",400);
        return result.get(0);

    }




}
