package com.example.quality_challenge.services;

import com.example.quality_challenge.dto.FlightDTO;
import com.example.quality_challenge.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class FlightServiceImpTest {

    @Mock
    private FlightRepository flightRepositoryMock;

    private FlightService flightService;

    @BeforeEach
    void setUp() {
        flightService = new FlightServiceImp(flightRepositoryMock);
        when(flightRepositoryMock.getFlights()).thenReturn(new ArrayList<>());
    }

    @ParameterizedTest
    @MethodSource("provideMapsToFilter")
    void filterFlights(Map<String,String> query, Integer quantity) {
        List<FlightDTO> flightDTOS = flightService.filterFlights(query);
        assertEquals(flightDTOS.size(),quantity);
    }

    List<Arguments> provideMapsToFilter(){
        return Arrays.asList(
                Arguments.of(Map.of(), 0)
        );
    }


}