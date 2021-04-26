package com.example.storage_practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String genero;
    private String fecha_nacimiento;
}
