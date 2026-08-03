package com.example.MyProject.AUTH.common;

public abstract class BaseController {
  protected <T> ApiResponse<T> createSuccessResponse(T data){
      return  ApiResponse.succcess(data);
  }
  protected <T> ApiResponse<T> creatSucessTRessponsWithmessage(String message,T data){
      return ApiResponse.succcessWithmessage(message,data);
  }
}
