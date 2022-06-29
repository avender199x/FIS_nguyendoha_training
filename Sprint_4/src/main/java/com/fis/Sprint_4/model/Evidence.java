package com.fis.Sprint_4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Evidence")
public class Evidence extends AbstractEntity {
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "criminal_Case_id")
    private CriminalCase criminalCase;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "storage_id")
    private Storage storage;
    private String number;
    private String itemName;
    @Lob
    private String notes;
    private Boolean archived = false;
    @OneToMany(mappedBy = "evidence")
    @JsonBackReference
    private Set<TrackEntry> trackEntries = new HashSet<>();
}