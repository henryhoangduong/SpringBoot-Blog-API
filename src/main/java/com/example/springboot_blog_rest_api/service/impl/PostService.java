package com.example.springboot_blog_rest_api.service.impl;

import com.example.springboot_blog_rest_api.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(long Id);
    PostDto updatePost(PostDto postDto,long Id);
    void deletePostById(long Id);
}
