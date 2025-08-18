package com.example.linkedin.posts_service.service;


import com.example.linkedin.posts_service.entity.PostLike;
import com.example.linkedin.posts_service.exceptions.BadRequestException;
import com.example.linkedin.posts_service.exceptions.ResourceNotFoundException;
import com.example.linkedin.posts_service.repository.PostLikeRepository;
import com.example.linkedin.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId, Long userId){
        log.info("attempting to like the post...");
        boolean exists= postRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with this post id");
        boolean alreadyLiked= postLikeRepository.existsByUserIdAndPostId(postId,userId);
        if(alreadyLiked) throw new BadRequestException("Cannot like the same post again.");

        PostLike postLike= new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

        log.info("post with id :{} liked successfully",postId);
    }

    public void unlikePost(Long postId, long userId) {
        log.info("attempting to unlike the post...");
        boolean exists= postRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with this post id");
        boolean alreadyLiked= postLikeRepository.existsByUserIdAndPostId(postId,userId);
        if(!alreadyLiked) throw new BadRequestException("Cannot unlike the same post again.");

       postLikeRepository.deleteByUserIdAndPostId(postId,userId);

        log.info("post with id :{} unliked successfully",postId);
    }
}
