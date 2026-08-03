package com.example.MyProject.AUTH.exception;


import com.example.MyProject.AUTH.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String,String>> handlerValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
           errors.put(error.getField(),error.getDefaultMessage());
        });
        return ApiResponse.errorListDataMessages(999,"List of error",errors);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handlerRuntimeException(RuntimeException ex){
        ApiResponse<Object> objectApiResponse= ApiResponse.error(999, ex.getMessage());
        return ResponseEntity.badRequest().body(objectApiResponse);
    }
}
