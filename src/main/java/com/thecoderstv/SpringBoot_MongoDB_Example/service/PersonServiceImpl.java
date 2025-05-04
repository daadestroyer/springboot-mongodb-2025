package com.thecoderstv.SpringBoot_MongoDB_Example.service;

import com.thecoderstv.SpringBoot_MongoDB_Example.collections.Person;
import com.thecoderstv.SpringBoot_MongoDB_Example.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public String savePerson(Person person) {
        return personRepo.save(person).getPersonId();
    }

    @Override
    public List<Person> getAllUser() {
        return personRepo.findAll();
    }

    @Override
    public Person findPersonById(String personId) {
        return personRepo.findById(personId).get();
    }

    @Override
    public void deletePersonById(String personId) {
        Person person = personRepo.findById(personId).get();
        personRepo.delete(person);
    }

    @Override
    public Person findPersonByFirstNameStartWith(String firstName) {
        return personRepo.findByFirstNameStartsWith(firstName);
    }

    @Override
    public List<Person> findPersonByAgeBetween(int minAge, int maxAge) {
        return personRepo.findByAgeBetween(minAge,maxAge);
    }
}
