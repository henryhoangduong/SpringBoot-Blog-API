package com.example.springboot_blog_rest_api.exception;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public BlogAPIException(HttpStatus httpStatus,String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
