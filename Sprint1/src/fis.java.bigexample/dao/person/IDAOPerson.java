package fis.java.bigexample.dao.person;

import fis.java.bigexample.model.Evidence;
import fis.java.bigexample.model.Person;

public interface IDAOPerson {
    public void add(Person person);
    public Person remove(int code);
    public Person update(Person person);
}
