package com.example.blog.until;

import com.example.blog.dto.Req.PostsDtoReq;
import com.example.blog.exception.PostsCharactersException;
import com.example.blog.exception.PostsTitleException;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public final class CheckPosts {
    public static void check(PostsDtoReq posts) {
        Boolean checkTitle = posts.getTitle().length() > 10 && posts.getTitle().length() < 60;
        if (!checkTitle) {
            throw new PostsTitleException("posts > 10 characters and posts < 100 characters");
        }
        Boolean checkPosts = posts.getPosts().length() > 15;
        if (!checkPosts) {
            throw new PostsCharactersException("posts > 15 characters");
        }
    }
}
