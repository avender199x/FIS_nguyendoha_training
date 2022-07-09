package com.example.blog.dto.Res;


import com.example.blog.entity.Posts;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostsDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String posts;

    public static PostsDto fromEntity(Posts posts) {
        return PostsDto.builder()
                .id(posts.getId())
                .createdAt(posts.getCreatedAt())
                .modifiedAt(posts.getModifiedAt())
                .title(posts.getTitle())
                .posts(posts.getPosts())
                .build();
    }
}
