package com.example.blog.dto.Res;

import com.example.blog.entity.*;
import com.example.blog.entity.Ennum.UserStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String name;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private List<PostsDto> postsDtos;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .status(user.getStatus())
                .postsDtos(user.getPosts().stream().map(PostsDto::fromEntity).collect(Collectors.toList()))
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .build();
    }

}
