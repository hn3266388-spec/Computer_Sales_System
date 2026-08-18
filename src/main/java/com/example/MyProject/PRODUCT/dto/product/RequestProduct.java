package com.example.MyProject.PRODUCT.dto.product;

import java.math.BigDecimal;


public record RequestProduct(
        String nameProduct,
        String slug,
        BigDecimal price,
        String specs,
        String image,
        String description,

        // Nhận ID của hãng và danh mục do Admin chọn từ dropdown (ví dụ: 1, 2)
        Long brandsId,
        Long categoriesId
) {
}
