package Model;

import java.time.LocalDateTime;
import java.util.Set;

public class Storage extends AbstractEntity {
    private String name;
    private String location;
    private Set<Evidence> evidenceSet;

    public Storage(Long id, int version, String name, String location, Set<Evidence> evidenceSet) {
        super(id, version);
        this.name = name;
        this.location = location;
        this.evidenceSet = evidenceSet;
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
