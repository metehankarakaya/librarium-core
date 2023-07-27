package com.librarium.core.app.post.controller;

import com.librarium.core.app.post.model.PostDTO;
import com.librarium.core.app.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class PostResource {

    private final PostServiceImpl postService;

    @PostMapping("/add/post")
    public ResponseEntity<Boolean> addPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.addPost(postDTO));
    }

    @PostMapping("/add/post/to/draft")
    public ResponseEntity<Boolean> addPostToDraft(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.addPostToDraft(postDTO));
    }

}
