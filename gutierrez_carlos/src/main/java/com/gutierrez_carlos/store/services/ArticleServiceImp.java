package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.exceptions.ArticleNotFoundException;
import com.gutierrez_carlos.store.exceptions.FilterErrorException;
import com.gutierrez_carlos.store.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleServiceImp implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * perform a stream sorting of an article list
     * @param list unordered list
     * @param orderParam order criteria
     * @return ordered list
     */
    public List<ArticleDTO> order(List<ArticleDTO> list, String orderParam)  {
        Stream<ArticleDTO> s = list.stream();
        switch (orderParam) {
            case "0":
                return s.sorted((a1, a2) -> a1.getProduct().compareTo(a2.getProduct())).collect(Collectors.toList());
            case "1":
                return s.sorted((a1, a2) -> a2.getProduct().compareTo(a1.getProduct())).collect(Collectors.toList());
            case "2":
                return s.sorted((a1, a2) -> a1.calculateDoublePrice().compareTo(a2.calculateDoublePrice())).collect(Collectors.toList());
            case "3":
                return s.sorted((a1, a2) -> a2.calculateDoublePrice().compareTo(a1.calculateDoublePrice())).collect(Collectors.toList());
            default:
                throw new FilterErrorException("Error al aplicar filtros, Parametro de ordenamiento invalido");
        }
    }

    /**
     * Method to get the article list and perform the filters specified by the user
     * @param query map of pre validated filters to apply
     * @return
     */
    @Override
    public List<ArticleDTO> filterArticles(Map<String,String> query){
        List<ArticleDTO> wholeList = articleRepository.listArticles();
        for (String filter:query.keySet()) {
            if(filter.equals("order"))
                continue;
            wholeList = wholeList
                    .stream()
                    .filter(articleDTO -> {
                        try {
                            Method getter = articleDTO.getClass().getMethod("get"+filter.substring(0, 1).toUpperCase() + filter.substring(1));
                            if(filter.equals("price"))
                                return String.valueOf(getter.invoke(articleDTO,null)).replaceAll("[^\\d]", "") .equals(query.get(filter));
                            return String.valueOf(getter.invoke(articleDTO,null)).toUpperCase().equals(query.get(filter).toUpperCase());
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }
        if (query.containsKey("order"))
            wholeList=order(wholeList,query.get("order"));
        if(wholeList.size()==0)
            throw new ArticleNotFoundException("No se encontraron articulos para los filtros especificados", HttpStatus.OK);
        return wholeList;
    }

}
