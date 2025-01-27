package com.example.springboot_blog_rest_api.repository;

import com.example.springboot_blog_rest_api.entity.Comment;
import com.example.springboot_blog_rest_api.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostsId(long postId);

}
