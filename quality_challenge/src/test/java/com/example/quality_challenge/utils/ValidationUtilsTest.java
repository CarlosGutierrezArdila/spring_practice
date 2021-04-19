package com.example.quality_challenge.utils;

import com.example.quality_challenge.Exceptions.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ValidationUtilsTest {



    @ParameterizedTest
    @DisplayName("Should perform flight query validations and throw exceptions")
    @MethodSource("provideMapsToFlightQueryValidation")
    void validateFlightQuery(Map<String,String> query , boolean givesException, String exceptionCode) {
        if(givesException){
            ApiException e = assertThrows(ApiException.class,()-> ValidationUtils.validateFlightQuery(query));
            assertEquals(e.getCode(),exceptionCode);
        } else
            assertDoesNotThrow(()-> ValidationUtils.validateFlightQuery(query));
    }

    List<Arguments> provideMapsToFlightQueryValidation(){
        return Arrays.asList(
                Arguments.of(Map.of("destination","Puerto null"), true , "F005"),
                Arguments.of(Map.of("origin","Puerto null", "destination","Puerto null","dateFrom","20/02/2020", "dateTo","19/02/2020"), true , "F004"),
                Arguments.of(Map.of("origin","Puerto null", "destination","Puerto null","dateFrom","20/02/2020", "dateTo","30/02/2020"), false , "")
        );
    }

    @ParameterizedTest
    @DisplayName("Should perform hotel query validations and throw exceptions")
    @MethodSource("provideMapsToHotelQueryValidation")
    void validateHotelQuery(Map<String,String> query , boolean givesException, String exceptionCode) {
        if(givesException){
            ApiException e = assertThrows(ApiException.class,()-> ValidationUtils.validateHotelQuery(query));
            assertEquals(e.getCode(),exceptionCode);
        } else
            assertDoesNotThrow(()-> ValidationUtils.validateHotelQuery(query));
    }

    List<Arguments> provideMapsToHotelQueryValidation(){
        return Arrays.asList(
                Arguments.of(Map.of("destination",""), true , "H004"),
                Arguments.of(Map.of("destination","Puerto null","dateFrom","20/02/2020", "dateTo","19/02/2020"), true , "H005"),
                Arguments.of(Map.of( "destination","Puerto null","dateFrom","20/02/2020", "dateTo","30/02/2020"), false , "")
        );
    }

}