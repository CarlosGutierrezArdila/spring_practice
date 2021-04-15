package com.example.quality_challenge.services;

import com.example.quality_challenge.dto.FlightDTO;

import java.util.List;
import java.util.Map;

public interface FlightService {
    List<FlightDTO> filterFlights(Map<String,String> query);

}
