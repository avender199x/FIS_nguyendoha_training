package com.fresher.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "Evidence")
public class Evidence extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "criminal_Case_id")
    private CriminalCase criminalCase;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "storage_id")
    private Storage storage;
    private String number;
    private String itemName;
    //very big text
    @Lob
    private String notes;
    private Boolean archived = false;
    @OneToMany(mappedBy = "evidence", cascade = CascadeType.ALL)
    private Set<TrackEntry> trackEntries = new HashSet<>();

    public CriminalCase getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCase criminalCase) {
        this.criminalCase = criminalCase;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    private void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

    public boolean addTrackEntry(TrackEntry trackEntry) {
        trackEntry.setEvidence(this);
        return trackEntries.add(trackEntry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Evidence evidence = (Evidence) o;
        return Objects.equals(criminalCase, evidence.criminalCase) &&
                Objects.equals(number, evidence.number) &&
                Objects.equals(itemName, evidence.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), criminalCase, number, itemName);
    }
}
