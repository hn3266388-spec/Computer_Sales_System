package com.example.MyProject.PRODUCT.mapper;

import com.example.MyProject.PRODUCT.dto.brands.BrandsRequest;
import com.example.MyProject.PRODUCT.dto.brands.BrandsResponse;
import com.example.MyProject.PRODUCT.entity.Brands;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BrandsMapper {
    Brands mapToentity(BrandsRequest brandsRequest);
    BrandsResponse mapToresponse(Brands brands);
    void updateBrands(BrandsRequest brandsRequest, @MappingTarget Brands brands);
}
