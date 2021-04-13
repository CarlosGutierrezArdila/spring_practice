package com.test.first_project.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.TreeMap;

@Data
@AllArgsConstructor

public class RomanNumber {

    private Integer numero;
    private String romanRespresentation;


    public RomanNumber(Integer numero) {
        this.numero = numero;
        this.setRomanRespresentation(RomanNumber.toRoman(this.numero));
    }

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public final static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }


}
