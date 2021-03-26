package com.test.first_project.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Greeting {
    private Long id;
    private String message;
    private String type;


}
