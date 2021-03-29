package com.calculators.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomDTO {
    private String nombre;
    private Double ancho;
    private Double largo;

    public Double calculateArea(){
        return this.ancho*this.largo;
    }
}
