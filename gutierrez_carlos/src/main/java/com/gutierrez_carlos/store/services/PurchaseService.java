package com.gutierrez_carlos.store.services;

import com.gutierrez_carlos.store.dto.PurchaseRequestDTO;
import com.gutierrez_carlos.store.dto.PurchaseResponseDTO;

public interface PurchaseService {
    public PurchaseResponseDTO processPurchase(PurchaseRequestDTO request);
}
