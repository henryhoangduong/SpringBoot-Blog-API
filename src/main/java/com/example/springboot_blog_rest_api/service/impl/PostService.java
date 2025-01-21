package com.example.springboot_blog_rest_api.service.impl;

import com.example.springboot_blog_rest_api.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
