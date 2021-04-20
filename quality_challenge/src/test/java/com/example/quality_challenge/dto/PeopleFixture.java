package com.example.quality_challenge.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeopleFixture {
    public static List<PersonDTO> getTwoPeople(){
        return new ArrayList<PersonDTO>(Arrays.asList(new PersonDTO(
                        "1234",
                        "test",
                        "test",
                        "01/01/2020",
                        "test@test.com"
                ),
                new PersonDTO(
                        "1234",
                        "test",
                        "test",
                        "01/01/2020",
                        "test@test.com")
        ));
    }
}
