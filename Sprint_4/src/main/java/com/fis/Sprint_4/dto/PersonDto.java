package com.fis.Sprint_4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class PersonDto extends AbstractEntityDto{
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;
}
