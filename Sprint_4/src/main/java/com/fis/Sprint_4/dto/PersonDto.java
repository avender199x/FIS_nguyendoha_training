package com.fis.Sprint_4.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonDto extends AbstractEntityDto{
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;
}
