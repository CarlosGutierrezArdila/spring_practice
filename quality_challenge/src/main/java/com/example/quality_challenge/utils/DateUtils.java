package com.example.quality_challenge.utils;

import com.example.quality_challenge.Exceptions.ApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static void validateDate(String date){
        try{
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception e){
            throw new ApiException("F002","Invalid date, format must be dd/mm/aaaa.",400);
        }
    }

    public static boolean afterStart(String date, String start){
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).minusDays(1);
        LocalDate start1 = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return  start1.isAfter(date1);
    }

    public static boolean beforeEnd(String date, String end){
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).plusDays(1);
        LocalDate end1 = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return end1.isBefore(date1);
    }

}
