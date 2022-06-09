package fis.java.bigexample.model;

import fis.java.bigexample.model.enums.EmploymentStatus;
import fis.java.bigexample.model.enums.Rank;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Detective {
    private Long id;
    private int version;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private  Person person;
    private String badgeNumber;
    private Rank rank;
    private  Boolean armed;
    private EmploymentStatus status;
    private Set<CriminalCase> criminalCases;
    private Set<TrackEntry> trackEntries;

    public Detective() {
    }

    public Detective(Long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, Person person,
                     String badgeNumber, Rank rank, Boolean armed, EmploymentStatus status,
                     Set<CriminalCase> criminalCases, Set<TrackEntry> trackEntries) {
        this.id = id;
        this.version = version;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.person = person;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.armed = armed;
        this.status = status;
        this.criminalCases = criminalCases;
        this.trackEntries = trackEntries;
    }

    public Detective(Long id, Person person,
                     String badgeNumber, Rank rank, Boolean armed, EmploymentStatus status) {
        this(id, 1, LocalDateTime.now(), LocalDateTime.now(), person, badgeNumber,rank, armed,status,
                new HashSet<CriminalCase>(), new HashSet<TrackEntry>());
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean getArmed() {
        return armed;
    }

    public void setArmed(Boolean armed) {
        this.armed = armed;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public Set<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    public void setCriminalCases(Set<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
    }

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    public void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }
}
