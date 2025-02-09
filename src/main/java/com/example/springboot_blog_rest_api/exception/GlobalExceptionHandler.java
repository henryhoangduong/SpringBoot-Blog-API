package com.example.springboot_blog_rest_api.exception;

import com.example.springboot_blog_rest_api.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceNotFoundException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogApiException(BlogAPIException blogAPIException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), blogAPIException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(AccessDeniedException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
