package com.fresher.model;

import com.fresher.core.CaseStatus;
import com.fresher.core.CaseType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
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
    //very big text
    private String notes;
    @OneToMany(mappedBy = "criminalCase", cascade = CascadeType.ALL)
    private Set<Evidence> evidenceSet = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "Detective_id")
    private Detective leadInvestigator;
    @ManyToMany
    @JoinTable(name = "working_detective_case",
            joinColumns = @JoinColumn(name = "criminal_Case_id"),
            inverseJoinColumns = @JoinColumn(name = "detective_id"))
    private Set<Detective> assigned = new HashSet<>();

    public CriminalCase() {
        super();
        this.status = CaseStatus.SUBMITTED;
        this.type = CaseType.UNCATEGORIZED;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSetArg) {
        evidenceSetArg.forEach(this::addEvidence);
    }

    public boolean addEvidence(Evidence evidence) {
        evidence.setCriminalCase(this);
        return evidenceSet.add(evidence);
    }

    public Detective getLeadInvestigator() {
        return leadInvestigator;
    }

    public void setLeadInvestigator(Detective leadInvestigator) {
        this.leadInvestigator = leadInvestigator;
    }

    public Set<Detective> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<Detective> assignedArg) {
        assignedArg.forEach(this::addDetective);
    }

    //case is assigned to the detective, always use this method
    public boolean addDetective(Detective detective) {
        detective.addCase(this);
        return assigned.add(detective);
    }
}
