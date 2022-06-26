package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fis.Sprint_4.core.EmploymentStatus;
import com.fis.Sprint_4.core.Rank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Detective")
public class Detective extends AbstractEntity {
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private String badgeNumber;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    private Boolean armed = false;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus status = EmploymentStatus.ACTIVE;
    @ManyToMany(mappedBy = "assigned")
    @JsonBackReference
    private Set<CriminalCase> criminalCases = new HashSet<>();
    @OneToMany(mappedBy = "detective")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TrackEntry> trackEntries;
}
