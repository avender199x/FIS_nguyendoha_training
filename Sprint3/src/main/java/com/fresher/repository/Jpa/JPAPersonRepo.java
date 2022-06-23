package com.fresher.repository.Jpa;

import com.fresher.model.Person;
import com.fresher.repository.PersonRepo;
import com.fresher.repository.jdbctemplate.AbstractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAPersonRepo extends AbstractRepo<Person> implements PersonRepo {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Person entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public Person update(Person entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(Person entity) {
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public int deleteById(Long entityId) {
        Person person = entityManager.find(Person.class, entityId);
        entityManager.remove(person);
        return entityId.intValue();
    }

    @Override
    public Optional<Person> findById(Long entityId) {
        return Optional.of(entityManager.find(Person.class, entityId));
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select p from Person p").getResultList();
    }
}
