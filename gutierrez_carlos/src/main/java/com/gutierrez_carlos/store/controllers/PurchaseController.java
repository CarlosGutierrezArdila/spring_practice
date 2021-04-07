package com.gutierrez_carlos.store.controllers;

import com.gutierrez_carlos.store.dto.PurchaseRequestDTO;
import com.gutierrez_carlos.store.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase-request")
    public ResponseEntity requestPurchase(@RequestBody PurchaseRequestDTO request){
        return ResponseEntity.status(200).body(purchaseService.processPurchase(request));
    }

}
