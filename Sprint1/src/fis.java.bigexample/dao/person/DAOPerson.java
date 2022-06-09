package fis.java.bigexample.dao.person;

import fis.java.bigexample.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DAOPerson implements IDAOPerson {
    private List<Person> personList = new ArrayList<>();

    @Override
    public void add(Person person) {
        for (Person person1 : this.personList) {
            if (person1.getId().equals(person.getId())) {
                throw new RuntimeException("Person ton tai");
            } else {
                this.personList.add(person);
                break;
            }
        }
    }

    @Override
    public Person remove(int code) {
        for (Person person : this.personList) {
            if (person.getId().equals((long) code)) {
                this.personList.remove(code);
                break;
            } else {
                throw new RuntimeException("Person ko ton tai");
            }
        }
        return null;
    }

    @Override
    public Person update(Person person) {
        for (Person person1 : this.personList) {
            if (person1.getId().equals(person.getId())) {
                this.personList.set(person.getId().intValue(), person);
                break;
            } else {
                throw new RuntimeException("Person ko ton tai");
            }
        }

        return null;
    }
}
