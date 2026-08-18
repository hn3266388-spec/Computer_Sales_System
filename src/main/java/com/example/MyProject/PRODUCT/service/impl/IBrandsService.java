package com.example.MyProject.PRODUCT.service.impl;

import com.example.MyProject.PRODUCT.dto.brands.BrandsRequest;
import com.example.MyProject.PRODUCT.dto.brands.BrandsResponse;
import com.example.MyProject.PRODUCT.entity.Brands;
import com.example.MyProject.PRODUCT.mapper.BrandsMapper;
import com.example.MyProject.PRODUCT.repository.BrandsRepository;
import com.example.MyProject.PRODUCT.service.BrandsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IBrandsService implements BrandsService {
    @Autowired
    private final BrandsRepository brandsRepository;
    @Autowired
    private final BrandsMapper brandsMapper;

    @Override
    public void CreateBrands(BrandsRequest brandsRequest) {
        brandsRepository.save(brandsMapper.mapToentity(brandsRequest));
    }

    @Override
    public void UpdateBrands(long id, BrandsRequest brandsRequest) {
           Brands brandsnew =brandsRepository.findById(id).orElseThrow(()-> new RuntimeException("brands not exist"));
           brandsMapper.updateBrands(brandsRequest,brandsnew);
           brandsRepository.save(brandsnew);
    }

    @Override
    public List<BrandsResponse> BrandsList() {
        List<Brands> brandsList = brandsRepository.findAll();
        return brandsList.stream()
                .map(brandsMapper::mapToresponse)
                .collect(Collectors.toList());
    }

    @Override
    public void DeleteBrands(long id) {
           brandsRepository.deleteById(id);
    }

    @Override
    public BrandsResponse searchBrands(String nameBrands) {
        BrandsResponse brandsResponse =brandsMapper.mapToresponse(brandsRepository.findByNameBrands(nameBrands).orElseThrow(()-> new RuntimeException("Brands not exist")));
        return brandsResponse;
    }
}
