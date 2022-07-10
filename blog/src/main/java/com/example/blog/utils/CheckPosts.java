package com.example.blog.utils;

import com.example.blog.dto.Req.PostsDtoReq;
import com.example.blog.exception.PostsCharactersException;
import com.example.blog.exception.PostsTitleException;

public final class CheckPosts {
    public static void check(PostsDtoReq posts) {
        boolean checkTitle = posts.getTitle().length() > 10 && posts.getTitle().length() < 60;
        if (!checkTitle) {
            throw new PostsTitleException("posts > 10 characters and posts < 100 characters");
        }
        boolean checkPosts = posts.getPosts().length() > 15;
        if (!checkPosts) {
            throw new PostsCharactersException("posts > 15 characters");
        }
    }
}
