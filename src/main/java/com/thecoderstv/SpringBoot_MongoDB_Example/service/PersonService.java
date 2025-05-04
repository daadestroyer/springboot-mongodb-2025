package com.thecoderstv.SpringBoot_MongoDB_Example.service;

import com.thecoderstv.SpringBoot_MongoDB_Example.collections.Person;

import java.util.List;

public interface PersonService {

    String savePerson(Person person);

    List<Person> getAllUser();

    Person findPersonById(String personId);

    void deletePersonById(String personId);

    Person findPersonByFirstNameStartWith(String firstName);

    List<Person> findPersonByAgeBetween(int minAge, int maxAge);
}
