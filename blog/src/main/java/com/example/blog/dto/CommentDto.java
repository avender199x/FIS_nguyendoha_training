package com.example.blog.dto;

import com.example.blog.entity.Comment;
import com.example.blog.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String comment;
    private UserInfoDto user;

    public static CommentDto fromEntity(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .comment(comment.getComment())
                .user(UserInfoDto.fromEntity(comment.getUser()))
                .build();
    }
}
