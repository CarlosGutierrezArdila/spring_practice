package com.example.quality_challenge.repositories;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.dto.FlightDTO;
import com.example.quality_challenge.dto.HotelDTO;
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
public class HotelRepositoryImp implements HotelRepository{

    private String filePath = "src/main/resources/dbHotels.json";
    private List<HotelDTO> hotels;
    private List<String> cities;

    public HotelRepositoryImp() {
        hotels = loadDatabase();
        cities = hotels.stream().map((hotelDTO)-> hotelDTO.getCity() ).collect(Collectors.toList());
    }

    public HotelRepositoryImp(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<HotelDTO> getHotels() {
        return hotels.stream().filter(hotelDTO -> !hotelDTO.getReserved()).collect(Collectors.toList());
    }

    @Override
    public boolean cityExists(String name) {
        return cities.contains(name);
    }

    @Override
    public HotelDTO getHotelByID(String codeHotel, String destination) {
        List<HotelDTO> result = hotels.stream().filter(hotelDTO ->
                hotelDTO.getCodeHotel().toUpperCase().equals(codeHotel.toUpperCase()) &&
                hotelDTO.getCity().toUpperCase().equals(destination.toUpperCase()))
                .collect(Collectors.toList());
        if (result.size()==0)
            throw new ApiException("H002", "there's no hotel matching the provided params", 400);
        return result.get(0);
    }

    public List<HotelDTO> loadDatabase(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<HotelDTO> hotels = new ArrayList<>();
        try {
            hotels = objectMapper.readValue(new File(filePath), new TypeReference<List<HotelDTO>>() {
            });
        } catch (Exception e){
            throw new ApiException("A002","Failed to load BD", 500);
        }
        return hotels;
    }




}
