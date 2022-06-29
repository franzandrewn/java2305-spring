package com.andrewn.java2305spring;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class PersonController {
    List<Person> persons;

    public PersonController() {
        persons = new LinkedList<>();
        persons.add(new Person(0, "Andrew", 23));
        persons.add(new Person(1, "Vasiliy", 25));
        persons.add(new Person(2, "Yulia", 22));
    }

    @GetMapping("/getsomeperson")
    public Person getSomePerson() {
        return persons.get((int) (Math.random() * persons.size()));
    }

    @GetMapping("/getpersons")
    public List<Person> getPersons() {
       return persons;
    }

    @GetMapping("/getperson/{index}")
    public Person getSpecificPerson(@PathVariable("index") int index) {
        return persons.get(index);
    }

    @PostMapping("/addperson")
    public int addPerson(@RequestBody Person newPerson) {
        newPerson.setId(persons.size());
        persons.add(newPerson);
        return persons.size() - 1;
    }

    @DeleteMapping("/deleteperson/{index}")
    public boolean deletePerson(@PathVariable("index") int index) {
        if (index < 0 || index >= persons.size()) {
            return false;
        } else {
            persons.remove(index);
            return true;
        }
    }
}
