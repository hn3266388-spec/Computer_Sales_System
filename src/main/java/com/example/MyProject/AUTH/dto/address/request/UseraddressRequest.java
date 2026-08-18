package com.example.MyProject.AUTH.dto.address.request;

import com.example.MyProject.AUTH.entity.Account;

public record UseraddressRequest(
        Long account,
        String username,
        String phone,
        String address,
        String defaultaddress
) {
}
