package com.example.springboot_blog_rest_api.service.impl;

import com.example.springboot_blog_rest_api.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(long Id);
}
