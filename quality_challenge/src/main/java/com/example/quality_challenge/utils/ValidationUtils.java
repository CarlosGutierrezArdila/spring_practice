package com.example.quality_challenge.utils;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.Exceptions.ExceptionFactory;
import com.example.quality_challenge.dto.FlightReservationDTO;
import com.example.quality_challenge.dto.HotelReservationDTO;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static void validateFlightQuery(Map<String,String> query){
        if(query.keySet().size()>0 && !query.keySet().containsAll(Arrays.asList("dateFrom","dateTo","origin","destination")))
            throw new ApiException("F005","The filter for flights must contain dateFrom, dateTo , origin and  destination parameters.",400);
        if (query.keySet().contains("dateFrom"))DateUtils.validateDate(query.get("dateFrom"));
        if (query.keySet().contains("dateTo"))DateUtils.validateDate(query.get("dateTo"));
        if (query.keySet().containsAll(Arrays.asList("dateFrom","dateTo")))
            if(DateUtils.beforeEnd(query.get("dateFrom"),query.get("dateTo")))
                throw new ApiException("F004","The final date must be after the starting date.",400);

    }

    public static void validateHotelQuery(Map<String, String> query) {
        if(query.keySet().size()>0 && !query.keySet().containsAll(Arrays.asList("dateFrom","dateTo","destination")))
            throw new ApiException("H004","The filter for hotels must contain dateFrom, dateTo and destination parameters.",400);
        if (query.keySet().contains("dateFrom"))DateUtils.validateDate(query.get("dateFrom"));
        if (query.keySet().contains("dateTo"))DateUtils.validateDate(query.get("dateTo"));
        if (query.keySet().containsAll(Arrays.asList("dateFrom","dateTo")))
            if(DateUtils.beforeEnd(query.get("dateFrom"),query.get("dateTo")))
                throw new ApiException("H005","The final date must be after the starting date.",400);
    }

    public static void validateEmail(String email){
        if (!Pattern.compile("^(.+)@(.+)$").matcher(email).matches())
            throw new ApiException("A006", "Invalid email, please provide a valid email", 400);

    }

    public static void validateBookFlightInput(FlightReservationDTO reservation){
        validateEmail(reservation.getUserName());
        DateUtils.validateDate(reservation.getBooking().getDateFrom());
        DateUtils.validateDate(reservation.getBooking().getDateTo());
        if(DateUtils.beforeEnd(reservation.getBooking().getDateFrom(),reservation.getBooking().getDateTo()))
            throw new ApiException("H005","The final date must be after the starting date.",400);
        if(reservation.getBooking().getPeople().size()!=reservation.getBooking().getSeats())
            throw new ApiException("F008","The specified number of seats does not match the number of people",400);
        if(reservation.getBooking().getPaymentMethod().getType().equalsIgnoreCase("DEBIT")&&reservation.getBooking().getPaymentMethod().getDues()!=1)
            throw new ApiException("F009","The specified payment method is debit and must have 1 due",400);

    }

    public static void validateBookHotelInput(HotelReservationDTO reservation){
        validateEmail(reservation.getUserName());
        DateUtils.validateDate(reservation.getBooking().getDateFrom());
        DateUtils.validateDate(reservation.getBooking().getDateTo());
        if(DateUtils.beforeEnd(reservation.getBooking().getDateFrom(),reservation.getBooking().getDateTo()))
            throw new ApiException("H005","The final date must be after the starting date.",400);
        if(reservation.getBooking().getPaymentMethod().getType().equalsIgnoreCase("DEBIT")&&reservation.getBooking().getPaymentMethod().getDues()!=1)
            throw new ApiException("F009","The specified payment method is debit and must have 1 due",400);
    }

}
