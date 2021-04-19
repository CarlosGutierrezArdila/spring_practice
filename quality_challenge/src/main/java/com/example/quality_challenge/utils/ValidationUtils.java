package com.example.quality_challenge.utils;

import com.example.quality_challenge.Exceptions.ApiException;
import com.example.quality_challenge.Exceptions.ExceptionFactory;

import java.util.Arrays;
import java.util.Map;

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
}
