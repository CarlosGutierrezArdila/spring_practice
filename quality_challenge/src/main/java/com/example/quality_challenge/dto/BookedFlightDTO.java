package com.example.quality_challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedFlightDTO {
    private String userName;
    private Double amount;
    private Double interest;
    private Double total;
    private FlightBookingResponseDTO booking;
}
