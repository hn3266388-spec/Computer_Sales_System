package com.example.MyProject.PRODUCT.repository;

import com.example.MyProject.PRODUCT.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {

}
