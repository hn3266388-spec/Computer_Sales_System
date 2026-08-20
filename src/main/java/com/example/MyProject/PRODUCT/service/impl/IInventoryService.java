package com.example.MyProject.PRODUCT.service.impl;

import com.example.MyProject.PRODUCT.dto.inventory.InventoryRequest;
import com.example.MyProject.PRODUCT.dto.inventory.InventoryResponse;
import com.example.MyProject.PRODUCT.entity.Inventory;
import com.example.MyProject.PRODUCT.entity.Products;
import com.example.MyProject.PRODUCT.mapper.InventoryMapper;
import com.example.MyProject.PRODUCT.repository.InventoryRepository;
import com.example.MyProject.PRODUCT.repository.ProductRepository;
import com.example.MyProject.PRODUCT.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IInventoryService implements InventoryService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final InventoryRepository inventoryRepository;
    @Autowired
    private final InventoryMapper inventoryMapper;

    @Override
    public void createInventory(InventoryRequest inventoryRequest) {
        Optional<Inventory> inventory = inventoryRepository.findByProducts_Id(inventoryRequest.productId());
        if (inventory.isPresent()) {
            throw new RuntimeException("Mỗi product chỉ đc create 1 lần");
        }
        Inventory inventory1=inventoryMapper.toEntity(inventoryRequest);
        inventory1.setSelled(0);
        inventoryRepository.save(inventory1);
    }
    @Transactional
    @Override
    public void updateSellInventory(Long id, int luongmua) {
        int trangthai = inventoryRepository.decreaseStock(id, luongmua);
        if (trangthai == 0) {
            throw new RuntimeException("đã hết sản phẩm trong kho");
        }
    }
   // @Transactional chỉ cần lấy đối tượng và set ko cần gọi repo để save
    @Override
    public void updateSoluong(Long id, int luongnhap) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Sản phẩm chưa được nhập số lượng"));
        inventory.setSoluong(luongnhap);
        inventoryRepository.save(inventory);
    }

    @Override
    public Page<InventoryResponse> showInventory(Pageable pageable) {
        Page<Inventory> inventoryResponses=inventoryRepository.findAll(pageable);
        return inventoryResponses.map(inventoryMapper::toResponse);
    }

    @Override
    public InventoryResponse searchInventory(String nameprodutc) {
        Inventory inventory=inventoryRepository.findByProducts_NameProduct(nameprodutc).orElseThrow(()-> new RuntimeException("Sản phẩm chưa được nhập số lượng"));
        InventoryResponse inventoryResponse=inventoryMapper.toResponse(inventory);
        return inventoryResponse;
    }
}
