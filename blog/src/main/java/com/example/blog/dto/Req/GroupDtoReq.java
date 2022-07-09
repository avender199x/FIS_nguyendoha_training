package com.example.blog.dto.Req;

import com.example.blog.entity.Posts;
import com.example.blog.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GroupDtoReq {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String groupName;
    private List<User> users;
    private List<Posts> posts;
}
