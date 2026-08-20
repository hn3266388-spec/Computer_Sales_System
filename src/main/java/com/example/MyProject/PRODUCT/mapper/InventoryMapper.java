package com.example.MyProject.PRODUCT.mapper;

import com.example.MyProject.PRODUCT.dto.categories.CategoriesRequest;
import com.example.MyProject.PRODUCT.dto.inventory.InventoryRequest;
import com.example.MyProject.PRODUCT.dto.inventory.InventoryResponse;
import com.example.MyProject.PRODUCT.entity.Categories;
import com.example.MyProject.PRODUCT.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InventoryMapper {

    @Mapping(target = "products.id", source = "productId")
    Inventory toEntity(InventoryRequest inventoryRequest);

    @Mapping(target = "productId", source = "products.id")
    @Mapping(target = "productName", source = "products.nameProduct")
    InventoryResponse toResponse(Inventory inventory);
    void updateInventory(InventoryRequest inventoryRequest, @MappingTarget Inventory inventory);
}
