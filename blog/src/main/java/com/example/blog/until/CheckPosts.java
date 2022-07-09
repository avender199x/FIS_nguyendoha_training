package com.example.blog.until;

import com.example.blog.dto.Req.PostsDtoReq;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public final class CheckPosts {

    public static Boolean check(PostsDtoReq posts) {
        Boolean checkTitle = posts.getTitle().length() > 10 && posts.getTitle().length() < 60;
        Boolean checkPosts = posts.getPosts().length() > 15;
        return checkTitle && checkPosts;
    }
}
