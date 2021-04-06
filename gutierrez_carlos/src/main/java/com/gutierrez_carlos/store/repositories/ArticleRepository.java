package com.gutierrez_carlos.store.repositories;

import com.gutierrez_carlos.store.dto.ArticleDTO;

import java.util.List;

public interface ArticleRepository {
    public List<ArticleDTO> listArticles();

}
