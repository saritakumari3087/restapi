package com.mars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mars.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
