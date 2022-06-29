package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fis.Sprint_4.core.EmploymentStatus;
import com.fis.Sprint_4.core.Rank;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "working_detective_case",
            joinColumns = @JoinColumn(name = "Detective_id")
            ,inverseJoinColumns =@JoinColumn(name = "criminal_Case_id"))
    @JsonBackReference
    private Set<CriminalCase> criminalCases;
    @OneToMany(mappedBy = "detective")
    @JsonBackReference
    private Set<TrackEntry> trackEntries;
}
