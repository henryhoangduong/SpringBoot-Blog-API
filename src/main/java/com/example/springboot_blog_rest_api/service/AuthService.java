package com.example.springboot_blog_rest_api.service;

import com.example.springboot_blog_rest_api.payload.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
