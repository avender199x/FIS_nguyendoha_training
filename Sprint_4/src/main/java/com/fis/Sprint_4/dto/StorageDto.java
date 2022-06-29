package com.fis.Sprint_4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageDto extends AbstractEntityDto {
    private String name;
    private String location;
}
