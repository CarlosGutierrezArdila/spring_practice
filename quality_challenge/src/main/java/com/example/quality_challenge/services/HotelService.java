package com.example.quality_challenge.services;

import com.example.quality_challenge.dto.BookedHotelDTO;
import com.example.quality_challenge.dto.HotelBookingDTO;
import com.example.quality_challenge.dto.HotelDTO;
import com.example.quality_challenge.dto.HotelReservationDTO;

import java.util.List;
import java.util.Map;

public interface HotelService {
    List<HotelDTO> filterHotels(Map<String,String> query);
    BookedHotelDTO bookHotel(HotelReservationDTO bookingRequest);
}
