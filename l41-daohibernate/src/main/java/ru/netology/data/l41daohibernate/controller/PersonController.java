package ru.netology.data.l41daohibernate.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.data.l41daohibernate.entity.Person;
import ru.netology.data.l41daohibernate.repository.PersonRepository;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons/by-city")
    public List<Person> personsByCity(String city) {
        return personRepository.getPersonsByCity(city);
    }
}
