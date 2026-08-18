package com.example.MyProject.PRODUCT.repository;

import com.example.MyProject.PRODUCT.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BrandsRepository extends JpaRepository<Brands,Long> {
   Optional<Brands>findByNameBrands(String nameBrands);
}
