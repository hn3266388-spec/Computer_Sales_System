package com.example.MyProject.AUTH.service.impl;


import com.example.MyProject.AUTH.dto.request.AccountRequest;
import com.example.MyProject.AUTH.dto.response.AccountResponse;
import com.example.MyProject.AUTH.entity.Account;
import com.example.MyProject.AUTH.mapper.AccountMapper;
import com.example.MyProject.AUTH.repository.AccountRepository;
import com.example.MyProject.AUTH.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IAccountService implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(AccountRequest rq) {
        Account ac=accountMapper.toEntity(rq);
        boolean checkgmail=accountRepository.existsBygmail(ac.getGmail());
        if(checkgmail==true){
          throw new RuntimeException("Tài khoản đã tồn tại");
        }
        ac.setPassword(passwordEncoder.encode(rq.password()));
        ac.setEnabled(false);
        ac.setRole("USER");
        accountRepository.save(ac);
    }

    @Override
    public void changeAccountStatus(Long id, Boolean enabled) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setEnabled(enabled);
        accountRepository.save(account);
    }

    @Override
    public Page<AccountResponse> listAccount(Pageable page) {
//        List<AccountResponse> accountResponses = new ArrayList<>();
//        List<Account> accounts = accountRepository.findAll();
//        for (Account ac : accounts) {
//            AccountResponse rq = accountMapper.toResponse(ac);
//            accountResponses.add(rq);
//        }
//        return accountResponses;
        Page<Account> accountPage=accountRepository.findAll(page);
        return accountPage.map(accountMapper::toResponse);
    }

    @Override
    public List<AccountResponse> searchByEmail(String accountname, String email) {
        List<Account> list = accountRepository.findByGmailOrAccountname(email, accountname);
        if(list.size()==0){
            throw new RuntimeException("User not exist");
        }
        List<AccountResponse> listrq= new ArrayList<>();
        for(Account ac : list){
            AccountResponse rq = accountMapper.toResponse(ac);
            listrq.add(rq);
        }

        return listrq;
    }
}