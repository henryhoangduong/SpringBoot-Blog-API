package com.example.springboot_blog_rest_api.service.impl;

import com.example.springboot_blog_rest_api.payload.PostDto;
import com.example.springboot_blog_rest_api.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);
    PostDto getPostById(long Id);
    PostDto updatePost(PostDto postDto,long Id);
    void deletePostById(long Id);
}
