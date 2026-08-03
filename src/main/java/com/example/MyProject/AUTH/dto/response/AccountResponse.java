package com.example.MyProject.AUTH.dto.response;

public record AccountResponse(
        Long id,
        String accountname,
        String gmail,
        String role,
        Boolean enabled
) {

}
