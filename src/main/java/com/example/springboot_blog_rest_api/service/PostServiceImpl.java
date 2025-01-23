package com.example.springboot_blog_rest_api.service;

import com.example.springboot_blog_rest_api.entity.Post;
import com.example.springboot_blog_rest_api.exception.ResourceNotFoundException;
import com.example.springboot_blog_rest_api.payload.PostDto;
import com.example.springboot_blog_rest_api.repository.PostRepository;
import com.example.springboot_blog_rest_api.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto to entity
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setDescription(postDto.getDescription());
        newPost.setContent(postDto.getContent());

        postRepository.save(newPost);
        //convert entity to Dto
        PostDto postDto1 = new PostDto();
        postDto1.setContent(newPost.getContent());
        postDto1.setDescription(newPost.getDescription());
        postDto1.setId(newPost.getId());
        return postDto1;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        return listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long Id) {
        Post post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long Id) {
        Post post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post upadatePost = postRepository.save(post);
        return mapToDto(upadatePost);
    }

    @Override
    public void deletePostById(long Id) {
        Post post = postRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", Id));
        postRepository.delete(post);
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setDescription(postDto.getDescription());
        newPost.setContent(postDto.getContent());
        return newPost;
    }
}
