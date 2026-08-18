package com.example.MyProject.PRODUCT.common;

public abstract class BaseController {
    protected <T> ApiResponse<T> successResponse(String message) {
        return ApiResponse.success(message);
    }

    protected <T> ApiResponse<T> successDataResponse(String message, T result) {
        return ApiResponse.successresult(message, result);
    }
}
