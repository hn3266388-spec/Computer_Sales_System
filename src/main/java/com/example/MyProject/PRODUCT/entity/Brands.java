package com.example.MyProject.PRODUCT.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="brands")
@Getter
@Setter
@NoArgsConstructor
public class Brands {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nameBrands;
    private String slug;
    private String logo;
    @OneToMany(mappedBy = "brands", cascade = CascadeType.ALL)
    private List<Products> products = new ArrayList<>();
}
