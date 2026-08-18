package com.example.MyProject.PRODUCT.service;

import com.example.MyProject.PRODUCT.dto.categories.CategoriesRequest;
import com.example.MyProject.PRODUCT.dto.categories.CategoriesResponse;
import com.example.MyProject.PRODUCT.dto.product.RequestProduct;
import com.example.MyProject.PRODUCT.entity.Categories;

import java.util.List;

public interface CategoriesService {
    void createCategories(CategoriesRequest categoriesRequest);
    void updateCategories(Long id,CategoriesRequest categoriesRequest);
    List<CategoriesResponse> showCategories();
    void deleteCategories(Long id);
}
