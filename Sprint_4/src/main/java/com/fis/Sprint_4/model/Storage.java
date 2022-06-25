package com.fis.Sprint_4.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Storage")
public class Storage extends AbstractEntity {
    private String name;
    private String location;
    @OneToMany(mappedBy = "storage")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Evidence> evidenceSet = new HashSet<>();
}
