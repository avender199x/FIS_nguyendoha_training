package Model;

import java.util.Date;

public class Student implements Comparable<Student> {
    private int code;
    private String name;
    private Date birthday;

    public Student(int code, String name, Date birthday) {
        this.code = code;
        this.name = name;
        this.birthday = birthday;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public int compareTo(Student student) {
        return this.name.compareTo(student.name);
    }
}
