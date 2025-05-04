package com.thecoderstv.SpringBoot_MongoDB_Example.controller;

import com.thecoderstv.SpringBoot_MongoDB_Example.collections.Person;
import com.thecoderstv.SpringBoot_MongoDB_Example.service.PersonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    @Operation(
            summary = "Save user api",
            description = "Save a single user"
    )
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        String personId = personService.savePerson(person);
        return new ResponseEntity<>(personId, HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Get all persons",
            description = "Return all users"
    )
    public ResponseEntity<?> getAllPerson() {
        List<Person> allUser = personService.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{personId}")
    @Operation(
            summary = "Get person by id",
            description = "Return person by id"
    )
    public ResponseEntity<?> getPersonById(@PathVariable String personId){
        Person personById = personService.findPersonById(personId);
        return new ResponseEntity<>(personById,HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    @Operation(
            summary = "Delete person by personId"
    )
    public ResponseEntity<?> deletePersonById(@PathVariable String personId){
        personService.deletePersonById(personId);
        return new ResponseEntity<>("Person deleted",HttpStatus.OK);
    }

    @GetMapping("/find-by-firstname{firstName}")
    @Operation(
            summary = "Find person by first name"
    )
    public ResponseEntity<?> getPersonByFirstName(@PathVariable String firstName){
        Person personByFirstNameStartWith = personService.findPersonByFirstNameStartWith(firstName);
        return new ResponseEntity<>(personByFirstNameStartWith,HttpStatus.OK);
    }

    @GetMapping("/find-by-age/{minAge}/{maxAge}")
    @Operation(
            summary = "Find user by age between"
    )
    public ResponseEntity<?> findPersonByAgeBetween(@PathVariable int minAge, @PathVariable int maxAge){
        List<Person> personByAgeBetween = personService.findPersonByAgeBetween(minAge, maxAge);
        return new ResponseEntity<>(personByAgeBetween,HttpStatus.OK);
    }
}
