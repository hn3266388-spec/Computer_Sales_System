package com.example.MyProject.AUTH.service;


import com.example.MyProject.AUTH.dto.request.AccountRequest;
import com.example.MyProject.AUTH.dto.response.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    void create(AccountRequest rq);
    void changeAccountStatus(Long id,Boolean enable);
    Page<AccountResponse> listAccount(Pageable page);
    List<AccountResponse> searchByEmail(String account, String email);
}
