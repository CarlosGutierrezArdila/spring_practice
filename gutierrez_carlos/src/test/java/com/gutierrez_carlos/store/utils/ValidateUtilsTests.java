package com.gutierrez_carlos.store.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;


class ValidateUtilsTests {

    @ParameterizedTest
    @CsvSource({"prestige,abc","notvalid,test","order,8","price,notnum"})
    void shouldThrowExceptionInvalidFilters(String key, String value){
        Map<String,String> query = new HashMap<>();
        query.put(key,value);
        assertThrows(RuntimeException.class, ()-> ValidateUtils.articleQueryValidator(query));
    }

    @ParameterizedTest
    @CsvSource({"prestige,***","product,test","order,2","price,$12.500"})
    void shouldBeValidFilters(String key, String value){
        Map<String,String> query = new HashMap<>();
        query.put(key,value);
        assertDoesNotThrow( ()-> ValidateUtils.articleQueryValidator(query));
    }

    @Test
    void tooManyFilters(){
        Map<String,String> query = new HashMap<>();
        query.put("price","10");
        query.put("product","10");
        query.put("category","10");
        assertThrows(RuntimeException.class, ()-> ValidateUtils.articleQueryValidator(query));

    }





}