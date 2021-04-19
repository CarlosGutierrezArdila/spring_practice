package com.example.quality_challenge.services;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.dto.*;
import com.example.quality_challenge.repositories.FlightRepository;
import com.example.quality_challenge.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightServiceImp implements FlightService{

    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImp(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<FlightDTO> filterFlights(Map<String, String> query) {
        List<FlightDTO> wholeList = flightRepository.getFlights();
        if(query.keySet().containsAll(Arrays.asList("origin","destination")) && (!flightRepository.originExists(query.get("origin")) || !flightRepository.destinationExists(query.get("destination"))) )
            throw new ApiException("F003","The specified origin or destination does not exist.",400);
        for (String filter:query.keySet()) {
            wholeList = wholeList
                    .stream()
                    .filter(FlightDTO -> {
                        try {
                            Method getter = FlightDTO.getClass().getMethod("get"+filter.substring(0, 1).toUpperCase() + filter.substring(1));
                            if(filter.contains("dateFrom"))
                                return DateUtils.afterStart(query.get(filter),FlightDTO.getDateFrom());
                            if(filter.contains("dateTo"))
                                return DateUtils.beforeEnd(query.get(filter),FlightDTO.getDateTo());
                            return String.valueOf(getter.invoke(FlightDTO,null)).toUpperCase().equals(query.get(filter).toUpperCase());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }
        if(wholeList.size()==0)
            throw new ApiException("F001","There is no flight matching the provided filters.",200);
        return wholeList;
    }

    @Override
    public BookedFlightDTO bookFlight(FlightReservationDTO flightRequest) {
        FlightBookingDTO flight = flightRequest.getBooking();
        FlightDTO bookedFlight = flightRepository.getFlightByID(flight.getFlightNumber(), flight.getOrigin(),flight.getDestination());
        Double fee = calculatePrice(bookedFlight, flight.getPeople().size());
        Double interest = calculateInterest(flight.getPaymentMethod().getDues());
        return new BookedFlightDTO(
                flightRequest.getUserName(),
                fee,
                interest,
                fee*(1.0+(interest/100.0)),
                new FlightBookingResponseDTO(
                        bookedFlight.getDateFrom(),
                        bookedFlight.getDateTo(),
                        bookedFlight.getOrigin(),
                        bookedFlight.getDestination(),
                        bookedFlight.getFlightNumber(),
                        flight.getSeats(),
                        flight.getSeatType(),
                        flight.getPeople(),
                        new MessageDTO(200,"Flight booked successfully")
                )

        );
    }

    public Double calculatePrice(FlightDTO flight , Integer people){
        return Double.parseDouble(flight.getPrice())*people;
    }

    public Double calculateInterest(Integer dues){
        if(dues==1)
            return 0.0;
        if(dues>1 && dues <4)
            return 5.0;
        if(dues>3)
            return 10.0;
        else
            return 0.0;
    }


}
