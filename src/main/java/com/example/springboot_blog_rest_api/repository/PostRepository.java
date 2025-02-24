package com.example.springboot_blog_rest_api.repository;

import com.example.springboot_blog_rest_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
