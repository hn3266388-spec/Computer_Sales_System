package com.example.MyProject.PRODUCT.dto.product;

import java.math.BigDecimal;

public record ResponseProduct(
        Long id,
        String nameProduct,
        String slug,
        BigDecimal price,
        String specs,
        String image,
        String description,

        // Trả về tên hãng và tên danh mục dưới dạng chữ (ví dụ: "ASUS", "Laptop")
        Long brandsId,
        Long categoriesId
) {
}
