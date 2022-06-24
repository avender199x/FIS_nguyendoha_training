package com.fis.Sprint_4.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Person")
public class Person extends AbstractEntity {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;
    private String newPassword;
}
