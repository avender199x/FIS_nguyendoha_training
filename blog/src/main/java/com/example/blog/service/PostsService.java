package com.example.blog.service;

import com.example.blog.dto.Req.PostsDtoReq;
import com.example.blog.dto.Res.PostsInfoDto;
import com.example.blog.entity.Posts;

public interface PostsService extends MethodBasic<PostsDtoReq, PostsInfoDto, Long> {
}
