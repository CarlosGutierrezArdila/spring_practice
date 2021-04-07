package com.gutierrez_carlos.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProductDTO {
    private Integer productId;
    private String name;
    private String brand;
    private Integer quantity;
}
