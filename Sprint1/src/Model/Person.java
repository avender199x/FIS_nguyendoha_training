package Model;

import java.time.LocalDateTime;

public class Person extends AbstractEntity {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private LocalDateTime hiringDate;

    public Person(Long id, int version, String username,
                  String firstname, String lastname,
                  String password, LocalDateTime hiringDate) {
        super(id, version);
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.hiringDate = hiringDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }
}
