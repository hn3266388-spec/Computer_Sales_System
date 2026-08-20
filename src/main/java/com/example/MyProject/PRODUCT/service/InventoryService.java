package com.example.MyProject.PRODUCT.service;

import com.example.MyProject.PRODUCT.dto.inventory.InventoryRequest;
import com.example.MyProject.PRODUCT.dto.inventory.InventoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InventoryService {
    void createInventory(InventoryRequest inventoryRequest);
    void updateSellInventory(Long id,int luongmua);
    void updateSoluong(Long id,int luongnhap);
    Page<InventoryResponse> showInventory(Pageable pageable);
    InventoryResponse searchInventory(String nameprodutc);
}
