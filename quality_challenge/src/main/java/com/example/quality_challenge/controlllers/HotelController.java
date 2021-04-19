package com.example.quality_challenge.controlllers;

import com.example.quality_challenge.dto.FlightReservationDTO;
import com.example.quality_challenge.dto.HotelReservationDTO;
import com.example.quality_challenge.services.HotelService;
import com.example.quality_challenge.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    
    

    @GetMapping("/hotels")
    public ResponseEntity getFlights(@RequestParam Map<String,String> query){
        ValidationUtils.validateHotelQuery(query);
        return ResponseEntity.ok().body(hotelService.filterHotels(query));
    }

    @PostMapping("/booking")
    public ResponseEntity bookFlight(@RequestBody HotelReservationDTO reservation){
        return ResponseEntity.ok().body(hotelService.bookHotel(reservation));
    }
}
