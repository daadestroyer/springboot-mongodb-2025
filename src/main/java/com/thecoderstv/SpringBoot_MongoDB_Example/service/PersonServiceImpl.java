package com.thecoderstv.SpringBoot_MongoDB_Example.service;

import com.thecoderstv.SpringBoot_MongoDB_Example.collections.Person;
import com.thecoderstv.SpringBoot_MongoDB_Example.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

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
        return personRepo.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Page<Person> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        // added criteria when name is not null and name is not empty
        if (name != null && !name.isEmpty()) {
            // added where condition and mapped firstName key to its value name which we have passed in out method
            criteria.add(Criteria.where("firstName").regex(name, "i"));
        }
        if (minAge != null && maxAge != null) {
            criteria.add(Criteria.where("age").gt(minAge).lte(maxAge));
        }
        if (city != null && !city.isEmpty()) {
            criteria.add(Criteria.where("address.city").is(city));
        }
        if (!criteria.isEmpty()) {
            // convert criteria list to array and passing it to andOperator
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Person.class),
                pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Person.class)
        );
    }


}
