package com.example.springboot_blog_rest_api.controller;

import com.example.springboot_blog_rest_api.payload.LoginDto;
import com.example.springboot_blog_rest_api.payload.RegisterDto;
import com.example.springboot_blog_rest_api.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login(LoginDto loginDto){
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new  ResponseEntity(response, HttpStatus.CREATED);
    }
}
