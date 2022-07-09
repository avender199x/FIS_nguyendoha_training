package com.example.blog.until;

import com.example.blog.entity.Posts;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public final class CheckPosts {
    @Autowired
    private static UserRepository userRepository;

    public static Boolean check(Posts posts) {
        Boolean checkUser = userRepository.findById(posts.getUser().getId()).isEmpty();
        Boolean checkTitle = posts.getTitle().length() > 10 && posts.getTitle().length() < 60;
        Boolean checkPosts = posts.getPosts().length() > 15;
        return checkUser && checkTitle && checkPosts;
    }
}
