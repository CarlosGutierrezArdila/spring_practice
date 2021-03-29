package com.calculators.demo.dto.area;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class HouseDTO {
    private String nombre;
    private String direccion;
    private List<RoomDTO> habitaciones;

}
