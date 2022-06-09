package fis.java.bigexample.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Evidence {
    private Long id;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private CriminalCase criminalCase;
    private Storage storage;
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;
    private Set<TrackEntry> trackEntries;

    public Evidence() {
    }

    public Evidence(Long id, int version, CriminalCase criminalCase, Storage storage, String number,
                    String itemName, String notes, Boolean archived, Set<TrackEntry> trackEntries) {
        this.id = id;
        this.version = version;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.criminalCase = criminalCase;
        this.storage = storage;
        this.number = number;
        this.itemName = itemName;
        this.notes = notes;
        this.archived = archived;
        this.trackEntries = trackEntries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

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

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    public void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }
}
