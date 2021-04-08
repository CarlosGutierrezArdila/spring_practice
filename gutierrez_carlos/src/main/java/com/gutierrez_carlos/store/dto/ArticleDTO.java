package com.gutierrez_carlos.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private Integer productId;
    private String product;
    private String category;
    private String brand;
    private String price;
    private Integer quantity;
    private String freeShipping;
    private String prestige;

    /**
     * create new product from a string list matching a csv entry
     * @param id
     * @param dataArray
     */
    public ArticleDTO(Integer id, List<String> dataArray) {
        this.productId = id;
        this.product = dataArray.get(0);
        this.category = dataArray.get(1);
        this.brand = dataArray.get(2);
        this.price = dataArray.get(3);
        this.quantity = Integer.parseInt(dataArray.get(4));
        this.freeShipping = dataArray.get(5);
        this.prestige = dataArray.get(6);
    }

    /**
     * obtain numeric value of price to make calculations
     * @return
     */
    public Double calculateDoublePrice(){
        return Double.parseDouble(getPrice().replaceAll("[^\\d]", ""));
    }

    public Boolean calculateBooleanFreeShipping(){
        return getFreeShipping().equals("SI");
    }

}
