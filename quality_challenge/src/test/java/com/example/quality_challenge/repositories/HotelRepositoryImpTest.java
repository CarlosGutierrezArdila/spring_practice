package com.example.quality_challenge.repositories;

import com.example.quality_challenge.Exceptions.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class HotelRepositoryImpTest {

    private HotelRepositoryImp hotelRepository;

    @BeforeEach
    void setUp() {
        hotelRepository=new HotelRepositoryImp("src/test/resources/testHotels.json");
        hotelRepository.setHotels(hotelRepository.loadDatabase());
        hotelRepository.setCities(hotelRepository.getHotels().stream().map((hotelDTO)-> hotelDTO.getCity() ).collect(Collectors.toList()));
    }

    @Test
    void getHotels() {
        assertEquals(2, hotelRepository.getHotels().size());
    }

    @Test
    void cityExists() {
        assertFalse(hotelRepository.cityExists("guerto ipuazu"));
        assertTrue(hotelRepository.cityExists("Puerto Iguazú"));
    }

    @Test
    void getHotelByID() {
        assertThrows(ApiException.class, ()-> hotelRepository.getHotelByID("",""));
        assertFalse(hotelRepository.getHotelByID("CH-0002","Puerto Iguazú").getReserved());
    }

    @Test
    @DisplayName("Should fail to load DB")
    void loadDatabase() {
        hotelRepository.setFilePath("src/test/resources/badFile.json");
        assertThrows(ApiException.class,()->hotelRepository.loadDatabase());
    }
}