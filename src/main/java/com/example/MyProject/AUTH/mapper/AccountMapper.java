package com.example.MyProject.AUTH.mapper;


import com.example.MyProject.AUTH.dto.auth.request.AccountRequest;
import com.example.MyProject.AUTH.dto.auth.response.AccountResponse;
import com.example.MyProject.AUTH.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {
    Account toEntity (AccountRequest rq);
    AccountResponse toResponse(Account account);
    void updateusserFromRq(AccountRequest rq, @MappingTarget Account account);
}
