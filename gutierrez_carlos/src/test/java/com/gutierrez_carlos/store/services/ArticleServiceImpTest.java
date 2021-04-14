package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.dto.ArticleDTOFixture;
import com.gutierrez_carlos.store.repositories.ArticleRepository;
import com.gutierrez_carlos.store.utils.ValidateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
@WebMvcTest(ArticleServiceImp.class)
class ArticleServiceImpTest {

    @MockBean
    private ArticleRepository articleRepositoryMock;

    @Autowired
    ArticleServiceImp articleServiceImp;

    @ParameterizedTest
    @MethodSource("provideMapsToFilter")
    void filterArticles(Map<String,String> query, Integer quantity) {
        when(articleRepositoryMock.listArticles()).thenReturn(ArticleDTOFixture.get4());
        List<ArticleDTO> response = articleServiceImp.filterArticles(query);
        Assertions.assertEquals(response.size(),quantity);
    }

    @ParameterizedTest
    @MethodSource("provideMapsToException")
    void filterArticlesFail(Map<String,String> query) {
        when(articleRepositoryMock.listArticles()).thenReturn(ArticleDTOFixture.get4());
        assertThrows(RuntimeException.class, ()-> articleServiceImp.filterArticles(query));
    }

    List<Arguments> provideMapsToFilter(){
        return Arrays.asList(
                Arguments.of(Map.of("product","test"), 1),
                Arguments.of(Map.of("category","herramientas"), 2),
                Arguments.of(Map.of("price","50000"), 1),
                Arguments.of(Map.of("order","0"), 4),
                Arguments.of(Map.of("order","1"), 4),
                Arguments.of(Map.of("order","2"), 4),
                Arguments.of(Map.of("order","3"), 4)
        );
    }

    List<Arguments> provideMapsToException(){
        return Arrays.asList(
                Arguments.of(Map.of("produkt","test")),
                Arguments.of(Map.of("category","herramientaz")),
                Arguments.of(Map.of("order","70"), 4)
        );
    }


}