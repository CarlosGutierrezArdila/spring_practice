package com.example.quality_challenge.utils;

import com.example.quality_challenge.Exceptions.ApiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void validateDate() {
        assertThrows(ApiException.class, ()-> DateUtils.validateDate("abc"));
        assertThrows(ApiException.class, ()-> DateUtils.validateDate("45/86/9999"));
        assertDoesNotThrow(()->DateUtils.validateDate("08/06/1997"));
    }

    @Test
    void afterStart() {
        assertFalse(DateUtils.afterStart("02/02/2020","01/02/2020"));
        assertTrue(DateUtils.afterStart("02/02/2020","02/02/2020"));
        assertTrue(DateUtils.afterStart("03/01/2020","01/02/2020"));
    }

    @Test
    void beforeEnd() {
        assertFalse(DateUtils.beforeEnd("01/02/2020", "02/02/2020"));
        assertTrue(DateUtils.beforeEnd("02/02/2020","02/02/2020"));
        assertFalse(DateUtils.beforeEnd("03/01/2020","01/02/2020"));
    }
}