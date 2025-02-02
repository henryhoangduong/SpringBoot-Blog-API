package com.example.springboot_blog_rest_api.service.impl;

import com.example.springboot_blog_rest_api.entity.Post;
import com.example.springboot_blog_rest_api.exception.ResourceNotFoundException;
import com.example.springboot_blog_rest_api.payload.PostDto;
import com.example.springboot_blog_rest_api.payload.PostResponse;
import com.example.springboot_blog_rest_api.repository.PostRepository;
import com.example.springboot_blog_rest_api.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
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
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post newPost = modelMapper.map(postDto, Post.class);
        return newPost;
    }
}
