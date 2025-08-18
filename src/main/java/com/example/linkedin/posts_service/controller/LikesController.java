package com.example.linkedin.posts_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.linkedin.posts_service.service.PostLikeService;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postlikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId){
            postlikeService.likePost(postId,1L);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId){
        postlikeService.unlikePost(postId,1L);
        return ResponseEntity.noContent().build();
    }
}
