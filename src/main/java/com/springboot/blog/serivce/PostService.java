package com.springboot.blog.serivce;

import com.springboot.blog.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost();

    void deletePost(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    PostDto getSinglePost(Long id);


}
