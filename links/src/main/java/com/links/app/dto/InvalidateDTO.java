package com.links.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidateDTO {
    private String url;
    private final String mensaje = "El link ha sido invalidado";
}
