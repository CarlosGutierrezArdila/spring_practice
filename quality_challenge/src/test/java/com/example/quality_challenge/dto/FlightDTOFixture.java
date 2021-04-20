package com.example.quality_challenge.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightDTOFixture {
    public static String completeFlightsListJson() {
        return "[\n" +
                "    {\n" +
                "        \"flightNumber\": \"BAPI-1235\",\n" +
                "        \"origin\": \"Buenos Aires\",\n" +
                "        \"destination\": \"Puerto Iguazú\",\n" +
                "        \"seatType\": \"Economy\",\n" +
                "        \"price\": \"6500\",\n" +
                "        \"dateFrom\": \"10/02/2021\",\n" +
                "        \"dateTo\": \"15/02/2021\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"flightNumber\": \"PIBA-1420\",\n" +
                "        \"origin\": \"Puerto Iguazú\",\n" +
                "        \"destination\": \"Bogotá\",\n" +
                "        \"seatType\": \"Business\",\n" +
                "        \"price\": \"43200\",\n" +
                "        \"dateFrom\": \"10/02/2021\",\n" +
                "        \"dateTo\": \"20/02/2021\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"flightNumber\": \"PIBA-1420\",\n" +
                "        \"origin\": \"Puerto Iguazú\",\n" +
                "        \"destination\": \"Bogotá\",\n" +
                "        \"seatType\": \"Economy\",\n" +
                "        \"price\": \"25735\",\n" +
                "        \"dateFrom\": \"10/02/2021\",\n" +
                "        \"dateTo\": \"21/02/2021\"\n" +
                "      }\n" +
                "]";
    }

    public static List<FlightDTO> completeFlightsList() {
        try {
            return new ObjectMapper().readValue(completeFlightsListJson(), new TypeReference<List<FlightDTO>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FlightDTO oneFlight() {
        return new FlightDTO(
                "PIBA-1420",
                "Puerto Iguazú",
                "Bogotá",
                "Economy",
                "10000",
                "10/02/2021",
                "20/02/2021"
        );
    }

    public static FlightReservationDTO bookInput() {
        return new FlightReservationDTO(
                "test@test.com.co",
                new FlightBookingDTO(
                        oneFlight().getDateFrom(),
                        oneFlight().getDateTo(),
                        oneFlight().getOrigin(),
                        oneFlight().getDestination(),
                        oneFlight().getFlightNumber(),
                        2,
                        oneFlight().getSeatType(),
                        PeopleFixture.getTwoPeople(),
                        new PaymentMethodDTO(
                                "CREDIT",
                                "000-000-000",
                                3
                        )
                )
        );
    }



}
