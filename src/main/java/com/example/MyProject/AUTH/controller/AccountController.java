package com.example.MyProject.AUTH.controller;


import com.example.MyProject.AUTH.common.ApiResponse;
import com.example.MyProject.AUTH.common.BaseController;
import com.example.MyProject.AUTH.dto.request.AccountRequest;
import com.example.MyProject.AUTH.dto.response.AccountResponse;
import com.example.MyProject.AUTH.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account")
public class AccountController extends BaseController {
    private final AccountService accountService;

    @GetMapping
    public ApiResponse<Page<AccountResponse>> showAccount(
            @RequestParam(name="page_no",defaultValue = "0") int page,
            @RequestParam(name="page_size",defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return ApiResponse.succcess(accountService.listAccount(pageable));
    }
    @PostMapping
    public ApiResponse<String> createAccount(@Valid @RequestBody AccountRequest accountRequest){
        accountService.create(accountRequest);
        return createSuccessResponse("Create Account successs");
    }
    @PutMapping("/{id}/enable")
    public ApiResponse<String> updateAccount(@PathVariable("id") Long id,@RequestBody Boolean enabled){
        accountService.changeAccountStatus(id,enabled);
        return createSuccessResponse("update enable success");
    }
    @GetMapping("/gmail")
    public ApiResponse<List<AccountResponse>> searchAccount( @RequestParam(required = false) String accountname,@RequestParam(required = false) String gmail){
        List<AccountResponse> listrq=accountService.searchByEmail(accountname,gmail);
        return creatSucessTRessponsWithmessage("Đã tìm thấy Account!",listrq);
    }
}
