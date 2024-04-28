package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.serivce.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postcontroller/")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        PostDto postcreted = postService.createPost(postDto);

        return new ResponseEntity<>(postcreted, HttpStatus.OK);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDto>> getAllPost(){
        //Get all posts
        List<PostDto> posts = postService.getAllPost();

        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

    @GetMapping("/getSinglePost/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Long id)
    {
        return new ResponseEntity<>(postService.getSinglePost(id), HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){

        postService.deletePost(id);

        return new ResponseEntity<>("Post deleted !", HttpStatus.OK);

    }

    @PutMapping("/updatePost/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long id)
    {
        PostDto updatedPost = postService.updatePost(postDto, id);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);

    }


}
