package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.dto.ArticleDTOFixture;
import com.gutierrez_carlos.store.repositories.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ArticleServiceImp.class)
class ArticleServiceImpTest {

    @MockBean
    private ArticleRepository articleRepositoryMock;

    @InjectMocks
    ArticleServiceImp articleServiceImp;

    @Test
    void filterArticles() {
        Map<String,String> param = new HashMap<>();
        when(articleRepositoryMock.listArticles()).thenReturn(ArticleDTOFixture.get4());
        List<ArticleDTO> response = articleServiceImp.filterArticles(param);
        Assertions.assertEquals(response.size(),4);

    }
}