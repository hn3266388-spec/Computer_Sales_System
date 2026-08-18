package com.example.MyProject.AUTH.dto.address.response;

import com.example.MyProject.AUTH.entity.Account;

public record UseraddressResponse(
        Long id,
        Long accountId,
        String username,
        String phone,
        String address,
        String defaultaddress
) {
}
