package com.example.blog.dto.Req;

import com.example.blog.dto.Res.CommentDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostsDtoReq {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String posts;
    private Long user;
    List<CommentDto> comments;
}
