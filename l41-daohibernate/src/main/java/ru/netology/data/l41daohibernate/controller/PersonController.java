package ru.netology.data.l41daohibernate.controller;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.data.l41daohibernate.entity.Person;
import ru.netology.data.l41daohibernate.repository.PersonRepository;
import ru.netology.data.l41daohibernate.request.PersonByNameAndSurnameRequest;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons/by-city")
    public List<Person> personsByCity(@Validated @NotEmpty String city) {
        return personRepository.findPersonsByCityOfLivingIgnoreCase(city);
    }

    @GetMapping("/persons/by-age-less-than")
    public List<Person> personsByAgeLessThanOrderByAgeAsc(@Validated @Positive int age) {
        return personRepository.findPersonsByPrimaryKey_AgeLessThanOrderByPrimaryKey_AgeAsc(age);
    }

    @GetMapping("/person/by-name-and-surname")
    public Person personByNameAndSurname(@Validated PersonByNameAndSurnameRequest request) {
        return personRepository
                .findPersonByPrimaryKey_NameAndPrimaryKey_Surname(request.getName(), request.getSurname())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
