package com.gutierrez_carlos.store.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.dto.ArticleDTOFixture;
import com.gutierrez_carlos.store.services.ArticleService;
import com.gutierrez_carlos.store.utils.ValidateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTests {

    @MockBean
    private ArticleService articleServiceMock;

    @Autowired
    private MockMvc mockMvc;


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllArticles() throws Exception {
        when(articleServiceMock.filterArticles(any())).thenReturn(ArticleDTOFixture.get4());
        MvcResult result = mockMvc.perform(get("/articles")).andExpect(status().isOk()).andReturn();
        List<ArticleDTO> articlesList = objectMapper.readValue(result.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });
        Assertions.assertEquals(articlesList.size(),4);

    }
}
