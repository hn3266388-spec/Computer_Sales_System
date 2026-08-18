package com.example.MyProject.PRODUCT.service.impl;

import com.example.MyProject.PRODUCT.dto.categories.CategoriesRequest;
import com.example.MyProject.PRODUCT.dto.categories.CategoriesResponse;
import com.example.MyProject.PRODUCT.entity.Categories;
import com.example.MyProject.PRODUCT.mapper.CategoriesMapper;
import com.example.MyProject.PRODUCT.repository.CategoriesRepository;
import com.example.MyProject.PRODUCT.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ICategoriesService implements CategoriesService {
    @Autowired
    private final CategoriesRepository categoriesRepository;
    @Autowired
    private final CategoriesMapper categoriesMapper;
    @Override
    public void createCategories(CategoriesRequest categoriesRequest) {
        categoriesRepository.save(categoriesMapper.mapToentity(categoriesRequest));
    }

    @Override
    public void updateCategories(Long id, CategoriesRequest categoriesRequest) {
        Categories categories=categoriesRepository.findById(id).orElseThrow(()->new RuntimeException("Categories not exist!"));
        categoriesMapper.updateCategories(categoriesRequest,categories);
        categoriesRepository.save(categories);
    }

    @Override
    public List<CategoriesResponse> showCategories() {
        List<Categories> listCategorise= categoriesRepository.findAll();
        List<CategoriesResponse> categoriesResponseList= new ArrayList<>();
        for(Categories cate : listCategorise){
            CategoriesResponse categoriesResponse= categoriesMapper.mapTorespon(cate);
            categoriesResponseList.add(categoriesResponse);
        }
        return categoriesResponseList;
    }

    @Override
    public void deleteCategories(Long id) {
         categoriesRepository.deleteById(id);
    }
}
