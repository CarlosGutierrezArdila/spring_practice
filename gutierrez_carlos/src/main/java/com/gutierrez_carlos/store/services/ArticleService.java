package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.exceptions.ArticleNotFoundException;
import com.gutierrez_carlos.store.exceptions.FilterErrorException;

import java.util.List;

public interface ArticleService {

    public List<ArticleDTO> listArticles();
    public List<ArticleDTO> filterArticles(String product,String category,String brand,String price,String freeShipping,String prestige,Integer order) throws ArticleNotFoundException, FilterErrorException;

}
