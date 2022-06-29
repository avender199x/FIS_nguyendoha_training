package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Storage")
public class Storage extends AbstractEntity {
    private String name;
    private String location;
    @OneToMany(mappedBy = "storage")
    @JsonBackReference
    private Set<Evidence> evidenceSet = new HashSet<>();
}
