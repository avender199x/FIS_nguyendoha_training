package com.example.blog.repository;

import com.example.blog.entity.Group;
import com.example.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByPhone(String phone);

    public Optional<User> findByGroups(List<Group> groups);
}
