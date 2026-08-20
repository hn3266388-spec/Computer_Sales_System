package com.example.MyProject.PRODUCT.dto.inventory;

import jakarta.validation.constraints.NotBlank;

public record InventoryRequest(
        @NotBlank(message = "Tên sản phẩm không được để trống")
        Long productId,
        int soluong
) {
}
