package com.example.MyProject.PRODUCT.controller;

import com.example.MyProject.PRODUCT.common.ApiResponse;
import com.example.MyProject.PRODUCT.common.BaseController;
import com.example.MyProject.PRODUCT.common.ProcessPageable;
import com.example.MyProject.PRODUCT.dto.inventory.InventoryRequest;
import com.example.MyProject.PRODUCT.dto.inventory.InventoryResponse;
import com.example.MyProject.PRODUCT.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController extends BaseController {

    @Autowired
    private final InventoryService inventoryService;

    // 1. Tạo mới kho cho sản phẩm (Yêu cầu quyền ADMIN)
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> createInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.createInventory(inventoryRequest);
        return successResponse("Create inventory success!");
    }

    // 2. Mua hàng (Trừ tồn kho, tăng số lượng đã bán)
    @PutMapping("/{id}/sell")
    @PreAuthorize("hasAnyRole('USER')")
    public ApiResponse<String> updateSellInventory(
            @PathVariable("id") Long id,
            @RequestParam("luongmua") int luongmua
    ) {
        inventoryService.updateSellInventory(id, luongmua);
        return successResponse("Sell inventory success!");
    }

    // 3. Cập nhật lại số lượng tồn kho (Nhập hàng thêm - Yêu cầu quyền ADMIN)
    @PutMapping("/{id}/soluong")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> updateSoluong(
            @PathVariable("id") Long id,
            @RequestParam("luongnhap") int luongnhap
    ) {
        inventoryService.updateSoluong(id, luongnhap);
        return successResponse("Update stock quantity success!");
    }

    // 4. Lấy danh sách tồn kho có phân trang & sắp xếp
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<Page<InventoryResponse>> showInventory(
            @RequestParam(name = "page_no", defaultValue = "0") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = ProcessPageable.createPageable(page, size, sortBy, sortDir);
        Page<InventoryResponse> response = inventoryService.showInventory(pageable);
        return successDataResponse("Get inventory success", response);
    }

    // 5. Tìm kiếm kho theo tên sản phẩm
    @GetMapping("/search")
    public ApiResponse<InventoryResponse> searchInventory(
            @RequestParam("nameProduct") String nameProduct
    ) {
        InventoryResponse response = inventoryService.searchInventory(nameProduct);
        return successDataResponse("Search inventory success", response);
    }
}