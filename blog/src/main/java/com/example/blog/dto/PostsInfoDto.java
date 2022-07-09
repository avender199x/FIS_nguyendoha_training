package com.example.blog.dto;

import com.example.blog.entity.Comment;
import com.example.blog.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostsInfoDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String posts;
    private UserInfoDto user;
    List<CommentDto> comments;
}
