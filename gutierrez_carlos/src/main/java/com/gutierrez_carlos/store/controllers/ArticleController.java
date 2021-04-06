package com.gutierrez_carlos.store.controllers;

import com.gutierrez_carlos.store.dto.ErrorDTO;
import com.gutierrez_carlos.store.exceptions.ArticleNotFoundException;
import com.gutierrez_carlos.store.exceptions.DataLoadException;
import com.gutierrez_carlos.store.exceptions.FilterErrorException;
import com.gutierrez_carlos.store.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public ResponseEntity listArticles(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String freeShipping,
            @RequestParam(required = false) String prestige,
            @RequestParam(required = false) Integer order) throws ArticleNotFoundException, FilterErrorException {
        if(category!=null || product!=null || brand!=null || price!=null || freeShipping!=null || prestige!=null)
            return new ResponseEntity(this.articleService.filterArticles(product,category,brand,price,freeShipping,prestige,order), HttpStatus.OK);
        else
            return new ResponseEntity(this.articleService.listArticles(), HttpStatus.OK);

    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity articleNotFoundHandler(ArticleNotFoundException e){
        return new ResponseEntity(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataLoadException.class)
    public ResponseEntity dataLoadErrorHandler(ArticleNotFoundException e){
        return new ResponseEntity(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FilterErrorException.class)
    public ResponseEntity filteringErrorHandler(FilterErrorException e){
        return new ResponseEntity(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }




}
