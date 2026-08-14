package com.example.MyProject.AUTH.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        Map<String,Object> errorRole=Map.of("code",401,
                "message","Ban chua dang nhap hoac token het han!!");
        objectMapper.writeValue(response.getWriter(),errorRole);
    }
}
