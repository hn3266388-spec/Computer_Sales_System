package com.example.MyProject.PRODUCT.controller;

import com.example.MyProject.PRODUCT.common.ApiResponse;
import com.example.MyProject.PRODUCT.common.BaseController;
import com.example.MyProject.PRODUCT.dto.categories.CategoriesRequest;
import com.example.MyProject.PRODUCT.dto.categories.CategoriesResponse;
import com.example.MyProject.PRODUCT.repository.CategoriesRepository;
import com.example.MyProject.PRODUCT.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoriesController extends BaseController {
    @Autowired
    private final CategoriesService categoriesService;
    @GetMapping
    public ApiResponse<List<CategoriesResponse>> showCategories(){
        List<CategoriesResponse> categoriesResponseList= categoriesService.showCategories();
        return successDataResponse("Get Categories success",categoriesResponseList);
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> createCategories(@RequestBody CategoriesRequest categoriesRequest){
        categoriesService.createCategories(categoriesRequest);
        return successResponse("Create Categories success");
    }
    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> updateCategories(@PathVariable("id") Long id , @RequestBody CategoriesRequest categoriesRequest){
        categoriesService.updateCategories(id,categoriesRequest);
        return successResponse("Update Categories success");
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> deleteCategories(@PathVariable("id") Long id){
        categoriesService.deleteCategories(id);
        return successResponse("Delete Categories success");
    }
}
