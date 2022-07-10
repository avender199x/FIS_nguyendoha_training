package com.example.blog.repository;

import com.example.blog.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    public Optional<Posts> findByPosts(Posts posts);
}
