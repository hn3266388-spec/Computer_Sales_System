package com.example.MyProject.AUTH.controller;

import com.example.MyProject.AUTH.dto.address.request.UseraddressRequest;
import com.example.MyProject.AUTH.dto.address.response.UseraddressResponse;
import com.example.MyProject.AUTH.service.UseraddressService;
import com.example.MyProject.PRODUCT.common.ApiResponse;
import com.example.MyProject.PRODUCT.common.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/useraddresses")
public class UseradressController extends BaseController {
    @Autowired
    private final UseraddressService useraddressService;

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER')")
    public ApiResponse<List<UseraddressResponse>> listAddressesOfUser() {
        List<UseraddressResponse> useraddressResponses = useraddressService.listaddress();
        return successDataResponse("List Addresses User", useraddressResponses);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<Page<UseraddressResponse>> listUseradresses(
            @RequestParam(name = "page_no", defaultValue = "0") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int size,
            @RequestParam(name = "id") String sortBy,
            @RequestParam(name = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return successDataResponse("List addresses User on Admin",useraddressService.showList(pageable));
    }
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public ApiResponse<String> createAddresses(@RequestBody UseraddressRequest useraddressRequest){
        useraddressService.createUseraddress(useraddressRequest);
        return successResponse("Create Addresses success");
    }
    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyRole('USER')")
    public ApiResponse<String> updateAddresses(@PathVariable("id") Long id,@RequestBody UseraddressRequest useraddressRequest){
        useraddressService.updateUseraddress(id,useraddressRequest);
        return successResponse("Update Addressess success");
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('USER')")
    public ApiResponse<String> deleteAddresses(@PathVariable("id") Long id){
        useraddressService.deleteUseraddress(id);
        return successResponse("Delete Addresses success");
    }
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<List<UseraddressResponse>> searchAddresses(@RequestParam("nameUseraddress") String nameUseraddress){
        List<UseraddressResponse> useraddressResponses=useraddressService.searchUseraddress(nameUseraddress);
        return successDataResponse("Search success",useraddressResponses);
    }
}
