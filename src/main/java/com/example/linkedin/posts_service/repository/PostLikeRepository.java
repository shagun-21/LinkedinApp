package com.example.linkedin.posts_service.repository;

import com.example.linkedin.posts_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    boolean existsByUserIdAndPostId(Long postId, Long userId);

    @Transactional
    void deleteByUserIdAndPostId(Long postId, long userId);
}
