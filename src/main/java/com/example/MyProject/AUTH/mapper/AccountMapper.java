package com.example.MyProject.AUTH.mapper;


import com.example.MyProject.AUTH.dto.request.AccountRequest;
import com.example.MyProject.AUTH.dto.response.AccountResponse;
import com.example.MyProject.AUTH.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity (AccountRequest rq);
    AccountResponse toResponse(Account account);
   // void updateusserFromRq(AccountRequest rq, @MappingTarget Account account);
}
