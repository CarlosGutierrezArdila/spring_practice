package com.example.quality_challenge.repositories;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.dto.FlightDTO;
import com.example.quality_challenge.services.FlightServiceImp;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class FlightRepositoryImpTest {

    private FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        flightRepository = new FlightRepositoryImp("src/test/resources/testFlights.json");
    }

    @Test
    @DisplayName("Should fail to load file")
    void loadDatabase() {
        FlightRepositoryImp test = new FlightRepositoryImp();
        test.setFilePath("src/test/resources/badFile.json");
        assertThrows(ApiException.class, ()->test.loadDatabase());
    }

    @Test
    void getFlights() {
        List<FlightDTO> list = flightRepository.getFlights();
        assertEquals(2, list.size());
    }

    @Test
    void destinationExists() {
        assertFalse(flightRepository.destinationExists("barrancabermeja :v"));
        assertTrue(flightRepository.destinationExists("Bogotá"));
    }

    @Test
    void originExists() {
        assertFalse(flightRepository.originExists("noxiste"));
        assertTrue(flightRepository.originExists("Buenos Aires"));
    }

    @Test
    void getFlightByID() {
        assertThrows(ApiException.class, ()-> flightRepository.getFlightByID("","",""));
        assertEquals("6500", flightRepository.getFlightByID("BAPI-1235","Buenos Aires","Puerto Iguazú").getPrice());
    }
}