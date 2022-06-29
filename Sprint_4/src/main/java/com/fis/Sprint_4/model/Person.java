package com.fis.Sprint_4.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Person")
public class Person extends AbstractEntity {
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;
    private String newPassword;
}
