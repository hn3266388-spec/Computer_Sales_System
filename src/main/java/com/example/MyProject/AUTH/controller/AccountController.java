package com.example.MyProject.AUTH.controller;


import com.example.MyProject.AUTH.common.ApiResponse;
import com.example.MyProject.AUTH.common.BaseController;
import com.example.MyProject.AUTH.dto.request.AccountRequest;
import com.example.MyProject.AUTH.dto.request.LoginRequest;
import com.example.MyProject.AUTH.dto.response.AccountResponse;
import com.example.MyProject.AUTH.security.jwt.JwtService;
import com.example.MyProject.AUTH.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account")
public class AccountController extends BaseController {
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
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
    @PostMapping("/register")
    public ApiResponse<String> createAccount(@Valid @RequestBody AccountRequest accountRequest){
        accountService.create(accountRequest);
        return createSuccessResponse("Create Account successs");
    }
    @PutMapping("/{id}/enable")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> updateAccount(@PathVariable("id") Long id,@RequestBody Boolean enabled){
        accountService.changeAccountStatus(id,enabled);
        return createSuccessResponse("update enable success");
    }
    @GetMapping("/gmail")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<List<AccountResponse>> searchAccount( @RequestParam(required = false) String accountname,@RequestParam(required = false) String gmail){
        List<AccountResponse> listrq=accountService.searchByEmail(accountname,gmail);
        return creatSucessTRessponsWithmessage("Đã tìm thấy Account!",listrq);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String,String>>> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.gmail(),
                        loginRequest.password()
                        )
        );
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String accessToken= jwtService.generateToken(userDetails);
        Map<String, String> data = Map.of("accessToken", accessToken);
        ApiResponse<Map<String, String>> response = ApiResponse.succcess(data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
