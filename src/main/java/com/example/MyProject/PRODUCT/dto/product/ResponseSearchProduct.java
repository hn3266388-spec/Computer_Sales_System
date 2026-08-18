package com.example.MyProject.PRODUCT.dto.product;

import org.springframework.data.domain.Page;

public record ResponseSearchProduct(
        ResponseProduct responseProduct,
        Page<ResponseProduct> pageproduct
) {
}
