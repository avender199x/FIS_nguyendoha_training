package fis.criminal.model;

public class Storage extends AbstractEntity {
    private String name;
    private String location;

    public Storage(long id, int version, String name, String location) {
        super(id, version);
        this.name = name;
        this.location = location;
    }

    public Storage() {
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
}
