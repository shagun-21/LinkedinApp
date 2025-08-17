package com.example.linkedin.posts_service.service;

import com.example.linkedin.posts_service.dto.PostCreateRequestDto;
import com.example.linkedin.posts_service.dto.PostDto;
import com.example.linkedin.posts_service.entity.Post;
import com.example.linkedin.posts_service.exceptions.ResourceNotFoundException;
import com.example.linkedin.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    @Autowired
    private ModelMapper modelMapper;

    private final PostRepository postRepository;

    public PostDto createPost(PostCreateRequestDto postDto,Long userId) {
        Post post= modelMapper.map(postDto,Post.class);
        post.setUserId(userId);
        Post save = postRepository.save(post);
        return modelMapper.map(save,PostDto.class);
    }

    public PostDto getPostById(Long postId){
        Post post= postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("post not found with id: "+postId));
        return modelMapper.map(post,PostDto.class);
    }
}
