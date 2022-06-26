package com.fis.Sprint_4.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AbstractEntityDto {
    protected Long id;
    protected int version;
    protected LocalDateTime createdAt;
    protected LocalDateTime modifiedAt;
}
