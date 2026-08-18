package com.example.MyProject.PRODUCT.common;

public record ApiResponse<T>(
        int code,
        String message,
        T result
) {
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(1000, message, null);
    }
    public static <T> ApiResponse<T> successresult(String message,T result){
        return new ApiResponse<>(1001,message,result);
    }
}
