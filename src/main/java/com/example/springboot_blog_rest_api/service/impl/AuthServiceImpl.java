package com.example.springboot_blog_rest_api.service.impl;

import com.example.springboot_blog_rest_api.entity.Role;
import com.example.springboot_blog_rest_api.entity.User;
import com.example.springboot_blog_rest_api.exception.BlogAPIException;
import com.example.springboot_blog_rest_api.payload.LoginDto;
import com.example.springboot_blog_rest_api.payload.RegisterDto;
import com.example.springboot_blog_rest_api.repository.RoleRepository;
import com.example.springboot_blog_rest_api.repository.UserRepository;
import com.example.springboot_blog_rest_api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User login successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Username already exists!");
        }
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Email already exists!");

        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User register successfully";
    }

}
