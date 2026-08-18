package com.example.MyProject.PRODUCT.service.impl;

import com.example.MyProject.PRODUCT.dto.product.RequestProduct;
import com.example.MyProject.PRODUCT.dto.product.ResponseProduct;
import com.example.MyProject.PRODUCT.dto.product.ResponseSearchProduct;
import com.example.MyProject.PRODUCT.entity.Brands;
import com.example.MyProject.PRODUCT.entity.Categories;
import com.example.MyProject.PRODUCT.entity.Products;
import com.example.MyProject.PRODUCT.mapper.ProductMapper;
import com.example.MyProject.PRODUCT.repository.BrandsRepository;
import com.example.MyProject.PRODUCT.repository.CategoriesRepository;
import com.example.MyProject.PRODUCT.repository.ProductRepository;
import com.example.MyProject.PRODUCT.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IProductService implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final BrandsRepository brandsRepository;
    @Autowired
    private final CategoriesRepository categoriesRepository;
    @Override
    public void createProduct(RequestProduct requestProduct) {
//        Brands brands= brandsRepository.findById(requestProduct.brandsId()).orElseThrow(()->new RuntimeException("Brands not exits!"));
//        Categories categories=categoriesRepository.findById(requestProduct.categoriesId()).orElseThrow(()-> new RuntimeException("categorries not exist!"));
//        Products products= productMapper.toEntity(requestProduct);
//        products.setBrands(brands);
//        products.setCategories(categories);
//        productRepository.save(products);
        productRepository.save(productMapper.toEntity(requestProduct));
    }

    @Override
    public void updateProduct(Long id, RequestProduct requestProduct) {
        Products products= productRepository.findById(id).orElseThrow(()-> new RuntimeException("product not exist"));
        productMapper.updateProduct(requestProduct,products);
        productRepository.save(products);
    }

    @Override
    public Page<ResponseProduct> listWithCategories(Long id, Pageable pageable) {
        Page<Products> pageproduct= productRepository.findByCategories_Id(id,pageable);
        return pageproduct.map(productMapper::toReponse);
    }

    @Override
    public Page<ResponseProduct> listWithBrands(Long id, Pageable pageable) {
        Page<Products> pageproduct=productRepository.findByBrands_Id(id,pageable);
        return pageproduct.map(productMapper::toReponse);
    }

    @Override
    public Page<ResponseProduct> listWithCategoriesAndBrands(Long id1, Long id2, Pageable pageable) {
        Page<Products> pageproduct=productRepository.findByBrands_IdAndCategories_Id(id1,id2,pageable);
        return pageproduct.map(productMapper::toReponse);
    }

    @Override
    public Page<ResponseProduct> ListAll(Pageable pageable) {
        Page<Products> pageproduct= productRepository.findAll(pageable);
        return pageproduct.map(productMapper::toReponse);
    }

    @Override
    public void deleteProduct(Long id) {
          productRepository.deleteById(id);
    }

    @Override
    public ResponseSearchProduct searchProduct(String nameProduct,Pageable pageable) {
        Products products=productRepository.findByNameProduct(nameProduct).orElseThrow(()-> new RuntimeException("Product not exist!"));
        Long brandsid=products.getBrands().getId();
        Page<Products> pageproduct= productRepository.findByBrands_Id(brandsid,pageable);
        return new ResponseSearchProduct(productMapper.toReponse(products),pageproduct.map(productMapper::toReponse));
    }
}
