package com.thecoderstv.SpringBoot_MongoDB_Example.service;

import com.thecoderstv.SpringBoot_MongoDB_Example.collections.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {

    String savePerson(Person person);

    List<Person> getAllUser();

    Person findPersonById(String personId);

    void deletePersonById(String personId);

    Person findPersonByFirstNameStartWith(String firstName);

    List<Person> findPersonByAgeBetween(int minAge, int maxAge);
    Page<Person> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable);
}
