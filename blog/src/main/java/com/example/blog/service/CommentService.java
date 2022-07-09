package com.example.blog.service;


import com.example.blog.dto.Req.CommentDtoReq;
import com.example.blog.dto.Res.PostsInfoDto;

public interface CommentService {
    public PostsInfoDto save(CommentDtoReq commentDtoReq);

    public PostsInfoDto update(Long id, CommentDtoReq commentDtoReq);

    public void delete(Long id);
}