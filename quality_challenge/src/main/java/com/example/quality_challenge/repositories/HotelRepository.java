package com.example.quality_challenge.repositories;

import com.example.quality_challenge.dto.FlightDTO;
import com.example.quality_challenge.dto.HotelDTO;

import java.util.List;

public interface HotelRepository {
    List<HotelDTO> getHotels();
    boolean cityExists(String name);
    HotelDTO getHotelByID(String codeHotel, String destination);
}
