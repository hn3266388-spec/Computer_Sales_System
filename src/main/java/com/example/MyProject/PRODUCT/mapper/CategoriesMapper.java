package com.example.MyProject.PRODUCT.mapper;

import com.example.MyProject.PRODUCT.dto.categories.CategoriesRequest;
import com.example.MyProject.PRODUCT.dto.categories.CategoriesResponse;
import com.example.MyProject.PRODUCT.entity.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoriesMapper {
    Categories mapToentity(CategoriesRequest categoriesRequest);
    CategoriesResponse mapTorespon(Categories categories);
            void updateCategories(CategoriesRequest categoriesRequest, @MappingTarget Categories categories);
}
