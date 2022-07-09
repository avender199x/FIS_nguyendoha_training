package com.example.blog.dto;

import com.example.blog.entity.Ennum.UserStatus;
import com.example.blog.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@Builder
public class UserInfoDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String name;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public static UserInfoDto fromEntity(User user) {
        return UserInfoDto.builder()
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .name(user.getName())
                .phone(user.getPhone())
                .status(user.getStatus())
                .build();
    }
}
