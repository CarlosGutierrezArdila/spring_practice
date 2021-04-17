package com.example.quality_challenge.controlllers;

import com.example.quality_challenge.dto.FlightReservationDTO;
import com.example.quality_challenge.services.FlightService;
import com.example.quality_challenge.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ResponseEntity getFlights(@RequestParam Map<String,String> query){
        ValidationUtils.validateFlightQuery(query);
        return ResponseEntity.ok().body(flightService.filterFlights(query));
    }

    @PostMapping("/flight-reservation")
    public ResponseEntity bookFlight(@RequestBody FlightReservationDTO reservation){
        return ResponseEntity.ok().body(reservation);
    }
}
