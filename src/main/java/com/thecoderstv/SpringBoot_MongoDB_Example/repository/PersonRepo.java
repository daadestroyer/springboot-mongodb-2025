package com.thecoderstv.SpringBoot_MongoDB_Example.repository;

import com.thecoderstv.SpringBoot_MongoDB_Example.collections.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends MongoRepository<Person, String> {
    Person findByFirstNameStartsWith(String firstName);
    @Query("{'age': {$gt: ?0, $lt: ?1}}")
    List<Person> findByAgeBetween(int minAge, int maxAge);


}
