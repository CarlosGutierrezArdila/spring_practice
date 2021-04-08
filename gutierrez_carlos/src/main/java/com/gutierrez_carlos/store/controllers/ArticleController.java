package com.gutierrez_carlos.store.controllers;
import com.gutierrez_carlos.store.services.ArticleService;
import com.gutierrez_carlos.store.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Endpoint responsible for articles filtering and listing, as well as input validation
     * @param query Map of query params provided for the request
     * @return
     */
    @GetMapping("/articles")
    public ResponseEntity listArticles(@RequestParam Map<String,String> query){
        ValidateUtils.articleQueryValidator(query);
        return ResponseEntity.ok().body(articleService.filterArticles(query));
    }


}
