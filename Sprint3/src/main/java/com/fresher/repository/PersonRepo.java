package com.fresher.repository;

import com.fresher.model.Person;

import java.util.*;

public interface PersonRepo extends AbstractRepo<Person> {
    public List<Person> findAll();
}
