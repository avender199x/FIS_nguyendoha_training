package com.example.blog.dto.Res;

import com.example.blog.entity.Posts;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static PostsInfoDto fromEntity(Posts posts) {
        return PostsInfoDto.builder()
                .id(posts.getId())
                .createdAt(posts.getCreatedAt())
                .modifiedAt(posts.getModifiedAt())
                .title(posts.getTitle())
                .posts(posts.getPosts())
                .user(UserInfoDto.fromEntity(posts.getUser()))
                .comments(posts.getComments().stream()
                        .map(CommentDto::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
