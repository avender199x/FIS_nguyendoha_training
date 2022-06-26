package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fis.Sprint_4.core.CaseStatus;
import com.fis.Sprint_4.core.CaseType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Criminal_Case")
public class CriminalCase extends AbstractEntity {
    private String number;
    @Enumerated(EnumType.STRING)
    private CaseType type;
    private String shortDescription;
    private String detailedDescription;
    @Enumerated(EnumType.STRING)
    private CaseStatus status;
    @Lob
    private String notes;
    @OneToMany(mappedBy = "criminalCase")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Evidence> evidenceSet = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "Detective_id")
    private Detective leadInvestigator;
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "working_detective_case",
            joinColumns = @JoinColumn(name = "criminal_Case_id"),
            inverseJoinColumns = @JoinColumn(name = "detective_id"))
    private Set<Detective> assigned = new HashSet<>();
}
