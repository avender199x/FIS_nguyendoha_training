package com.example.blog.repository;

import com.example.blog.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
