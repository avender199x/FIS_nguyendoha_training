package com.example.blog.dto.Req;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDtoReq {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String comment;
    private Long user;
}
