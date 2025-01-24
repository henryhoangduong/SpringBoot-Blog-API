package com.example.springboot_blog_rest_api.service;

import com.example.springboot_blog_rest_api.payload.PostDto;
import com.example.springboot_blog_rest_api.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);
    PostDto getPostById(long Id);
    PostDto updatePost(PostDto postDto,long Id);
    void deletePostById(long Id);
}
