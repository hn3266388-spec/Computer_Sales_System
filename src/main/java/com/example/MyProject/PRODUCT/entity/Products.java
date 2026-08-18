package com.example.MyProject.PRODUCT.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brands_id")
    private Brands brands;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categories_id")
    private Categories categories;
    private String nameProduct;
    private String slug;
    private BigDecimal price;
    private String specs;
    private String image;
    private String description;
}
