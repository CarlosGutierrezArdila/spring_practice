package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.ArticleDTO;
import com.gutierrez_carlos.store.exceptions.ArticleNotFoundException;
import com.gutierrez_carlos.store.exceptions.FilterErrorException;
import com.gutierrez_carlos.store.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleServiceImp implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleDTO> listArticles() {
        return articleRepository.listArticles();
    }

    @Override
    public List<ArticleDTO> filterArticles(String product, String category, String brand, String price, String freeShipping, String prestige, Integer order) {
        if (Arrays.asList(product, category, brand, price, freeShipping, prestige)
                .stream().filter(key -> key != null)
                .collect(Collectors.toList()).size() > 2) {
            throw new FilterErrorException("Error al aplicar filtros maximo 2 filtros permitidos");
        }
        List<ArticleDTO> response = articleRepository.listArticles().stream()
                .filter(articleDTO -> {
                    Boolean result=false;
                        result = this.applyFilters(articleDTO, product, category, brand, price, freeShipping, prestige);

                    return result;
                })
                .collect(Collectors.toList());
        if (response.size() == 0)
            throw new ArticleNotFoundException("No se encontraron productos para el filtro especificado");
        if (order != null)
            response = order(response, order);
        return response;
    }

    public List<ArticleDTO> order(List<ArticleDTO> list, Integer orderParam)  {
        Stream<ArticleDTO> s = list.stream();
        switch (orderParam) {
            case 0:
                return s.sorted((a1, a2) -> a1.getProduct().compareTo(a2.getProduct())).collect(Collectors.toList());
            case 1:
                return s.sorted((a1, a2) -> a2.getProduct().compareTo(a1.getProduct())).collect(Collectors.toList());
            case 2:
                return s.sorted((a1, a2) -> a1.calculateDoublePrice().compareTo(a2.calculateDoublePrice())).collect(Collectors.toList());
            case 3:
                return s.sorted((a1, a2) -> a2.calculateDoublePrice().compareTo(a1.calculateDoublePrice())).collect(Collectors.toList());
            default:
                throw new FilterErrorException("Error al aplicar filtros, Parametro de ordenamiento invalido");
        }
    }


    public Boolean applyFilters(ArticleDTO target, String product, String category, String brand, String price, String freeShipping, String prestige) {
        Boolean result = true;
        try {
            if (product != null)
                result = result & target.getProduct().equals(product);
            if (category != null)
                result = result & target.getCategory().equals(category);
            if (brand != null)
                result = result & target.getBrand().equals(brand);
            if (price != null)
                result = result & Double.parseDouble(price.replaceAll("[^\\d]",""))==(target.calculateDoublePrice());
            if (freeShipping != null)
                result = result & target.getFreeShipping().equals(freeShipping);
            if (prestige != null)
                result = result & target.getPrestige().equals(prestige);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilterErrorException("Error al aplicar filtros valores incorrectos");
        }
        return result;
    }

}
