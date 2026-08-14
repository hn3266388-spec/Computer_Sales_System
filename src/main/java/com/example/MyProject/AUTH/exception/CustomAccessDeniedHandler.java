package com.example.MyProject.AUTH.exception;

import com.example.MyProject.AUTH.common.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
              response.setStatus(HttpServletResponse.SC_FORBIDDEN);
              response.setContentType("application/json");
              Map<String,Object> errorRole=Map.of("code",403,
                      "message","Ban khong đu quyen đe truy cap!");
              objectMapper.writeValue(response.getWriter(),errorRole);
    }
}
