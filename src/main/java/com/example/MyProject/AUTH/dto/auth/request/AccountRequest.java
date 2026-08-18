package com.example.MyProject.AUTH.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
public record AccountRequest(
        Long id,
        @NotBlank(message = "Name tài khoản không được để trống")
        String accountname,
        @Email(message = "Email không đúng định dạng")
        @NotBlank(message = "Email không được để trống")
        String gmail,
        @NotBlank(message = "Mật khẩu không được để trống")
        @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
                message = "Mật khẩu phải chứa chữ thường, chữ hoa, số và ký tự đặc biệt"
        )
        String password
) {
}
