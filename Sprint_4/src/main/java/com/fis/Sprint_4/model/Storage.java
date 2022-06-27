package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Storage")
public class Storage extends AbstractEntity {
    private String name;
    private String location;
    @OneToMany(mappedBy = "storage")
    @JsonBackReference
    private Set<Evidence> evidenceSet = new HashSet<>();
}
