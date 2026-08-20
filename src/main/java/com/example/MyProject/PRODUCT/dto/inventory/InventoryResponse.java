package com.example.MyProject.PRODUCT.dto.inventory;

public record InventoryResponse(
        Long id,
        Long productId,
        String productName,
        int soluong,
        int selled
) {
}
