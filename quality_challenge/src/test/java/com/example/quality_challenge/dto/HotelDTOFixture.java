package com.example.quality_challenge.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class HotelDTOFixture {
    public static final String hotelsListJson =
            "[\n" +
                    "    {\n" +
                    "        \"codeHotel\": \"CH-0002\",\n" +
                    "        \"name\": \"Cataratas Hotel\",\n" +
                    "        \"city\": \"Puerto Iguazú\",\n" +
                    "        \"roomType\": \"Doble\",\n" +
                    "        \"nightPrice\": \"10000\",\n" +
                    "        \"dateTo\": \"20/03/2021\",\n" +
                    "        \"dateFrom\": \"10/02/2021\",\n" +
                    "        \"reserved\": false\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"codeHotel\": \"CH-0003\",\n" +
                    "        \"name\": \"Cataratas Hotel 2\",\n" +
                    "        \"city\": \"Puerto Iguazú\",\n" +
                    "        \"roomType\": \"Triple\",\n" +
                    "        \"nightPrice\": \"8200\",\n" +
                    "        \"dateTo\": \"23/03/2021\",\n" +
                    "        \"dateFrom\": \"10/02/2021\",\n" +
                    "        \"reserved\": false\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"codeHotel\": \"HB-0001\",\n" +
                    "        \"name\": \"Hotel Bristol\",\n" +
                    "        \"city\": \"Buenos Aires\",\n" +
                    "        \"roomType\": \"Single\",\n" +
                    "        \"nightPrice\": \"5435\",\n" +
                    "        \"dateTo\": \"19/03/2021\",\n" +
                    "        \"dateFrom\": \"10/02/2021\",\n" +
                    "        \"reserved\": false\n" +
                    "    }\n" +
                    "]";

    public static List<HotelDTO> hotelsList(){
        try {
        return new ObjectMapper().readValue(hotelsListJson, new TypeReference<List<HotelDTO>>() {
        });
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static HotelDTO getOne(){
        return  hotelsList().get(0);
    }

    public static HotelReservationDTO bookingInput(){
        return new HotelReservationDTO(
                "test@test.com",
                new HotelBookingDTO(
                        getOne().getDateFrom(),
                        getOne().getDateTo(),
                        getOne().getCity(),
                        getOne().getCodeHotel(),
                        2,
                        "DOUBLE",
                        PeopleFixture.getTwoPeople(),
                        new PaymentMethodDTO(
                                "CREDIT",
                                "000-000-000",
                                1
                        )

                )
        );
    }
}
