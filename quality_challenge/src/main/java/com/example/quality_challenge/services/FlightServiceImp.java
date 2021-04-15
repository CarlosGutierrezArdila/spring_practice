package com.example.quality_challenge.services;

import com.example.quality_challenge.Exceptions.ExceptionFactory;
import com.example.quality_challenge.dto.FlightDTO;
import com.example.quality_challenge.repositories.FlightRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
        for (String filter:query.keySet()) {
            wholeList = wholeList
                    .stream()
                    .filter(FlightDTO -> {
                        try {
                            Method getter = FlightDTO.getClass().getMethod("get"+filter.substring(0, 1).toUpperCase() + filter.substring(1));
                            return String.valueOf(getter.invoke(FlightDTO,null)).toUpperCase().equals(query.get(filter).toUpperCase());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }
        if(wholeList.size()==0)
            throw ExceptionFactory.getException("E001");
        return wholeList;
    }
}
