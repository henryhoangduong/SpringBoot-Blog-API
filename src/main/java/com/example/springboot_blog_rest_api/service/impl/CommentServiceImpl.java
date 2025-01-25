package com.example.springboot_blog_rest_api.service.impl;

import com.example.springboot_blog_rest_api.entity.Comment;
import com.example.springboot_blog_rest_api.entity.Post;
import com.example.springboot_blog_rest_api.exception.ResourceNotFoundException;
import com.example.springboot_blog_rest_api.payload.CommentDto;
import com.example.springboot_blog_rest_api.repository.CommentRepository;
import com.example.springboot_blog_rest_api.repository.PostRepository;
import com.example.springboot_blog_rest_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentDto.getId());
        commentDto.setBody(commentDto.getBody());
        commentDto.setEmail(commentDto.getEmail());
        commentDto.setName(comment.getName());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        return comment;
    }
}
