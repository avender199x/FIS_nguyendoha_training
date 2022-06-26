package com.fis.Sprint_4.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Evidence")
public class Evidence extends AbstractEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "criminal_Case_id")
    private CriminalCase criminalCase;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storage_id")
    private Storage storage;
    private String number;
    private String itemName;
    @Lob
    private String notes;
    private Boolean archived = false;
    @OneToMany(mappedBy = "evidence", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TrackEntry> trackEntries = new HashSet<>();
}