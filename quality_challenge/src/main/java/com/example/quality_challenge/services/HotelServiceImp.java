package com.example.quality_challenge.services;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.dto.*;
import com.example.quality_challenge.repositories.HotelRepository;
import com.example.quality_challenge.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelServiceImp implements HotelService{

    private HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImp(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDTO> filterHotels(Map<String, String> query) {
        List<HotelDTO> response = new ArrayList<>();
        if (query.keySet().size()>0)
            response= hotelRepository.getHotels().stream()
                    .filter(hotel -> DateUtils.afterStart( hotel.getDateFrom() , query.get("dateFrom")) &&
                            DateUtils.beforeEnd( hotel.getDateTo(), query.get("dateTo") )&&
                            hotel.getCity().equals(query.get("destination")))
                    .collect(Collectors.toList());
        else return hotelRepository.getHotels();
        if(response.size()==0)
            throw new ApiException("H001", "There is no matching hotel for the provided filters", 200);
        return response;
    }

    @Override
    public BookedHotelDTO bookHotel(HotelReservationDTO bookingRequest) {
        HotelBookingDTO booking = bookingRequest.getBooking();
        HotelDTO hotel = hotelRepository.getHotelByID(booking.getHotelCode(), booking.getDestination());
        hotel.setReserved(true);
        return new BookedHotelDTO(
                bookingRequest.getUserName(),
                calculatePrice(hotel, booking.getPeopleAmount()),
                calculateInterest(booking.getPaymentMethod().getDues()),
                calculatePrice(hotel, booking.getPeopleAmount())*(1.0 + calculateInterest(booking.getPaymentMethod().getDues())/100.0),
                booking,
                new MessageDTO(200,"Hotel booked successfully")

        );
    }

    public Double calculatePrice( HotelDTO hotel , Integer people){
        return Double.parseDouble(hotel.getNightPrice())*people;
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
