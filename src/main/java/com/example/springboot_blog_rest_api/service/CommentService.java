package com.example.springboot_blog_rest_api.service;

import com.example.springboot_blog_rest_api.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto>  getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateComment(Long postId, long commentId,CommentDto commentRequest);
    void deleteComment(Long postId, Long commentId);
}
