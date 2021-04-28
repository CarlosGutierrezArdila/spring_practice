package com.example.demo_jpa.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String dni;
    private String name;
    private String last_name;

}
