package com.example.MyProject.PRODUCT.controller;

import com.example.MyProject.PRODUCT.common.ApiResponse;
import com.example.MyProject.PRODUCT.common.BaseController;
import com.example.MyProject.PRODUCT.dto.brands.BrandsRequest;
import com.example.MyProject.PRODUCT.dto.brands.BrandsResponse;
import com.example.MyProject.PRODUCT.service.BrandsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/brands")
public class BrandsController extends BaseController {
    @Autowired
    private final BrandsService brandsService;

    @GetMapping
    public ApiResponse<List<BrandsResponse>> showBrands(){
        List<BrandsResponse> list= brandsService.BrandsList();
        return successDataResponse("List Brands",list);
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> createBrands(@RequestBody BrandsRequest brandsRequest){
        brandsService.CreateBrands(brandsRequest);
        return successResponse("Create Brands success");
    }
    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> updateBrands(@PathVariable("id") Long id,@RequestBody BrandsRequest brandsRequest){
        brandsService.UpdateBrands(id,brandsRequest);
        return successResponse("Update Brands success");
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> deleteBrands(@PathVariable("id") Long id){
        brandsService.DeleteBrands(id);
        return successResponse("Delete Brands success");
    }
    @GetMapping("/search")
    public ApiResponse<BrandsResponse> searchBrands(@RequestParam("nameBrands") String nameBrands){
        BrandsResponse brandsResponse=brandsService.searchBrands(nameBrands);
        return successDataResponse("Search Brands success",brandsResponse);
    }
}
