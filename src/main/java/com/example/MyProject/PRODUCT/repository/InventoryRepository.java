package com.example.MyProject.PRODUCT.repository;

import com.example.MyProject.PRODUCT.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProducts_NameProduct(String nameProduct);

    Optional<Inventory> findByProducts_Id(Long productId);

    @Modifying
    @Query("UPDATE Inventory i SET i.soluong = i.soluong - :quantity, i.selled = i.selled + :quantity " +
            "WHERE i.products.id = :productId AND i.soluong >= :quantity")
    int decreaseStock(@Param("productId") Long productId, @Param("quantity") int quantity);
}

