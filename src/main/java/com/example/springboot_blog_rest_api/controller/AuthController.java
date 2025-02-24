package com.example.springboot_blog_rest_api.controller;

import com.example.springboot_blog_rest_api.payload.JWTAuthResponse;
import com.example.springboot_blog_rest_api.payload.LoginDto;
import com.example.springboot_blog_rest_api.payload.RegisterDto;
import com.example.springboot_blog_rest_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto.toString());
        String response = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(response);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto.toString());

        String response = authService.register(registerDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
