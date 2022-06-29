package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fis.Sprint_4.core.CaseStatus;
import com.fis.Sprint_4.core.CaseType;
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
    private Set<Evidence> evidenceSet = new HashSet<>();
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "Detective_id")
    private Detective leadInvestigator;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(mappedBy = "criminalCases", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JsonBackReference
    private Set<Detective> assigned;
}
