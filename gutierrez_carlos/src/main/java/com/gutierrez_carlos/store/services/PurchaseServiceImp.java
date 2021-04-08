package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.*;
import com.gutierrez_carlos.store.exceptions.InsuficientStockException;
import com.gutierrez_carlos.store.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PurchaseServiceImp implements PurchaseService{
    private ArticleRepository articleRepository;

    @Autowired
    public PurchaseServiceImp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    /**
     * Processes a purchase request verifying the current product stock
     * @param request
     * @return
     */
    @Override
    public PurchaseResponseDTO processPurchase(PurchaseRequestDTO request) {
        int total = 0;
        PurchaseResponseDTO response = new PurchaseResponseDTO();
        response.setTicket(new TicketDTO(1, new ArrayList<PurchaseProductDTO>(), 0));
        for (PurchaseProductDTO item:request.getArticles()) {
            ArticleDTO article = articleRepository.getArticleById(item.getProductId());
            item.setName(article.getProduct());
            item.setBrand(article.getBrand());
            response.getTicket().getArticles().add(item);
            if (item.getQuantity()>article.getQuantity())
                throw new InsuficientStockException("La cantidad de producto con id "+item.getProductId()+" supera la cantidad del stock");
            article.setQuantity(article.getQuantity()-item.getQuantity());
            total+=article.calculateDoublePrice()*item.getQuantity();
            response.getTicket().setTotal((int) total);
        }
        response.setStatusCode(new MessageDTO(200, "Compra exitosa"));
        return response;
    }
}
