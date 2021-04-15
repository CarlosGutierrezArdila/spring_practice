package com.gutierrez_carlos.store.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleDTOFixture {
    private static  Integer productId =1;
    private static  String product = "test";
    private static  String category = "test";
    private static  String brand = "test";
    private static  String price = "$1.000";
    private static  Integer quantity = 1;
    private static  String freeShipping = "SI";
    private static  String prestige = "****";


    public static ArticleDTO getOne() {
        return new ArticleDTO(productId,product,category,brand,price,quantity,freeShipping,prestige);
    }

    public static List<ArticleDTO> get4(){
        return new ArrayList<>(Arrays.asList(
                getOne(),
                new ArticleDTO(2,"test2","herramientas","test","$50.000",4,"SI","***"),
                new ArticleDTO(3,"test3","herramientas","test","$30.000",4,"SI","***"),
                new ArticleDTO(4,"test4","test","test","$30.000",4,"SI","***")
                ));
    }

}
