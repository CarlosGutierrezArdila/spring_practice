package com.gutierrez_carlos.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private String product;
    private String category;
    private String brand;
    private String price;
    private Integer quantity;
    private String freeShipping;
    private String prestige;

    public ArticleDTO(List<String> dataArray) {
        this.product = dataArray.get(0);
        this.category = dataArray.get(1);
        this.brand = dataArray.get(2);
        this.price = dataArray.get(3);
        this.quantity = Integer.parseInt(dataArray.get(4));
        this.freeShipping = dataArray.get(5);
        this.prestige = dataArray.get(6);
    }

    public Double calculateDoublePrice(){
        return Double.parseDouble(getPrice().replace("$", "").replace(".", ""));
    }

    public Boolean calculateBooleanFreeShipping(){
        return getFreeShipping().equals("SI");
    }

}
