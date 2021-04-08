package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.exceptions.ArticleNotFoundException;
import com.gutierrez_carlos.store.exceptions.FilterErrorException;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    public List<ArticleDTO> filterArticles(Map<String,String> query);

}
