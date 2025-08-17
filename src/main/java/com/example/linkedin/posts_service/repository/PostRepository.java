package com.example.linkedin.posts_service.repository;

import com.example.linkedin.posts_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
