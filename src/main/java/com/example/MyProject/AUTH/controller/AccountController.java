package com.example.MyProject.AUTH.controller;


import com.example.MyProject.AUTH.common.ApiResponse;
import com.example.MyProject.AUTH.common.BaseController;
import com.example.MyProject.AUTH.dto.auth.request.AccountRequest;
import com.example.MyProject.AUTH.dto.auth.request.LoginRequest;
import com.example.MyProject.AUTH.dto.auth.response.AccountResponse;
import com.example.MyProject.AUTH.entity.Account;
import com.example.MyProject.AUTH.entity.RefreshToken;
import com.example.MyProject.AUTH.repository.AccountRepository;
import com.example.MyProject.AUTH.security.jwt.JwtService;
import com.example.MyProject.AUTH.security.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;
    private final AccountRepository accountRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<Page<AccountResponse>> showAccount(
            @RequestParam(name = "page_no", defaultValue = "0") int page,
            @RequestParam(name = "page_size", defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ApiResponse.succcess(accountService.listAccount(pageable));
    }

    @PostMapping("/register")
    public ApiResponse<String> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        accountService.create(accountRequest);
        return createSuccessResponse("Create Account successs");
    }

    @PutMapping("/{id}/enable")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<String> updateAccount(@PathVariable("id") Long id, @RequestBody Boolean enabled) {
        accountService.changeAccountStatus(id, enabled);
        return createSuccessResponse("update enable success");
    }

    @GetMapping("/gmail")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<List<AccountResponse>> searchAccount(@RequestParam(required = false) String accountname, @RequestParam(required = false) String gmail) {
        List<AccountResponse> listrq = accountService.searchByEmail(accountname, gmail);
        return creatSucessTRessponsWithmessage("Đã tìm thấy Account!", listrq);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.gmail(),
                        loginRequest.password()
                )
        );
        Account account = (Account) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(account);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(account.getId());
        return ResponseEntity.ok(
                ApiResponse.succcess(
                        Map.of(
                                "accessToken", accessToken,
                                "refreshToken", refreshToken.getToken()
                        )
                )
        );
    }
    @PostMapping("/refresh-token")
    public ApiResponse<ResponseEntity<?>> refreshToken(@RequestBody Map<String,String> request){
        String refreshTokenStr= request.get("refreshToken");
        if(refreshTokenStr==null){
            throw new RuntimeException("Refresh token not null");
        }
        RefreshToken refreshToke=refreshTokenService.finByToken(refreshTokenStr).orElseThrow(()-> new RuntimeException("Refresh don't exist"));
        if(refreshToke.isRevoked()){
            throw new RuntimeException("Refresh token revoked");
        }
        RefreshToken verifiedToken= refreshTokenService.verifyExpiration(refreshToke);
        Account account= verifiedToken.getAccount();
        String newAccessToken = jwtService.generateToken(account);
        return ApiResponse.succcess(new ResponseEntity<>(Map.of("accessToken",newAccessToken),HttpStatus.OK));
    }
    @PostMapping("/logout")
    public ApiResponse<ResponseEntity<?>> logout(@RequestHeader("Authorization") String authorization){
        String token=authorization.substring(7);
        String gmail= jwtService.extractUsername(token);
        Account account= accountRepository.findBygmail(gmail).orElseThrow(()-> new RuntimeException("account not found"));
        refreshTokenService.revokeAllAcoountToken(account.getId());
        return ApiResponse.succcess(new ResponseEntity<>(Map.of("message","Logout Successfully"),HttpStatus.OK));
    }
}
