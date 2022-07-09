package com.example.blog.dto;

import com.example.blog.entity.Group;
import com.example.blog.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GroupDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String groupName;
    private List<UserInfoDto> user;
    private List<PostsInfoDto> posts;

    public static GroupDto fromEntity(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .createdAt(group.getCreatedAt())
                .modifiedAt(group.getModifiedAt())
                .groupName(group.getGroupName())
                .user(group.getUsers().stream().map(UserInfoDto::fromEntity).collect(Collectors.toList()))
                .posts(group.getPosts().stream().map(PostsInfoDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

}
