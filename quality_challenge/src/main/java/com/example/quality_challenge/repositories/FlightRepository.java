package com.example.quality_challenge.repositories;

import com.example.quality_challenge.dto.FlightDTO;

import java.util.List;

public interface FlightRepository {
    List<FlightDTO> getFlights();
}
