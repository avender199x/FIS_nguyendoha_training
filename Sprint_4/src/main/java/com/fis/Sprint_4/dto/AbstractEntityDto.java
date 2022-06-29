package com.fis.Sprint_4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractEntityDto {
    protected Long id;
    protected int version;
    protected LocalDateTime createdAt;
    protected LocalDateTime modifiedAt;
}
