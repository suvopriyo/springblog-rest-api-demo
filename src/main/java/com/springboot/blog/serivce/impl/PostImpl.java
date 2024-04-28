package com.springboot.blog.serivce.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.serivce.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostImpl implements PostService {

    private PostRepository postRepository;

    public PostImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);

        Post postcreated = postRepository.save(post);

        return mapToDto(postcreated);

    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();

        List<PostDto> postsdto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        return postsdto;

    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        postRepository.delete(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        //post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post postupdated = postRepository.save(post);

        return mapToDto(postupdated);




    }

    @Override
    public PostDto getSinglePost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id.toString()));

        return mapToDto(post);

    }


    public PostDto mapToDto(Post post)
    {
        PostDto postDto = new PostDto();

        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        postDto.setId(post.getId());

        return postDto;

    }

    public Post mapToEntity(PostDto postDto)
    {
        Post post = new Post();

        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        post.setId(postDto.getId());

        return post;
    }



}
