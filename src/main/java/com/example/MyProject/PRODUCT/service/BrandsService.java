package com.example.MyProject.PRODUCT.service;

import com.example.MyProject.PRODUCT.dto.brands.BrandsRequest;
import com.example.MyProject.PRODUCT.dto.brands.BrandsResponse;
import com.example.MyProject.PRODUCT.entity.Brands;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandsService {
    void CreateBrands (BrandsRequest brandsRequest);
    void UpdateBrands (long id,BrandsRequest brandsRequest);
    List<BrandsResponse> BrandsList();
    void DeleteBrands(long id);
    BrandsResponse searchBrands(String nameBrands);
}
