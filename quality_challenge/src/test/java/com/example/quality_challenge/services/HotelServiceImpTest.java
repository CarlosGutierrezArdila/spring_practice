package com.example.quality_challenge.services;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.dto.BookedHotelDTO;
import com.example.quality_challenge.dto.HotelDTOFixture;
import com.example.quality_challenge.repositories.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class HotelServiceImpTest {

    @Mock
    private HotelRepository hotelRepositoryMock;

    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelService = new HotelServiceImp(hotelRepositoryMock);
    }

    @ParameterizedTest
    @MethodSource("provideMapsToFilter")
    void filterHotels(Map<String,String> query, Integer quantity, boolean shouldThrow, String exceptionCode) {
        when(hotelRepositoryMock.getHotels()).thenReturn(HotelDTOFixture.hotelsList());
        if(shouldThrow)
            assertEquals(exceptionCode, assertThrows(ApiException.class, ()-> hotelService.filterHotels(query)));
        else
            assertEquals(quantity, hotelService.filterHotels(query).size());
    }

    List<Arguments> provideMapsToFilter(){
        return Arrays.asList(
                Arguments.of(Map.of(), 3, false, ""),
                Arguments.of(Map.of("destination","Buenos Aires", "dateFrom", "11/02/2021", "dateTo","20/02/2021"), 1,false,"")
        );
    }


    @Test
    void testBookHotel() {
        when(hotelRepositoryMock.getHotelByID(any(),any())).thenReturn(HotelDTOFixture.hotelsList().get(0));
        BookedHotelDTO result = hotelService.bookHotel(HotelDTOFixture.bookingInput());
        assertEquals(20000.0,result.getTotal());
        assertEquals(0.0,result.getInterest());
        assertEquals(200,result.getStatusCode().getCode());

    }
}