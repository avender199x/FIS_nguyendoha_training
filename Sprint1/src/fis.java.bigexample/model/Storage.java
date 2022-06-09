package fis.java.bigexample.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Storage {
    private Long id;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String name;
    private String location;
    private Set<Evidence> evidenceSet;

    public Storage() {
    }

    public Storage(Long id, int version, String name, String location, Set<Evidence> evidenceSet) {
        this.id = id;
        this.version = version;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.name = name;
        this.location = location;
        this.evidenceSet = evidenceSet;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Evidence> getEvidenceSet() {
        return evidenceSet;
    }

    public void setEvidenceSet(Set<Evidence> evidenceSet) {
        this.evidenceSet = evidenceSet;
    }
}
