package ru.netology.data.l70methodsec.controller;


import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.data.l41daohibernate.request.PersonByNameAndSurnameRequest;
import ru.netology.data.l70methodsec.entity.Person;
import ru.netology.data.l70methodsec.model.BasePermission;
import ru.netology.data.l70methodsec.model.BaseRole;
import ru.netology.data.l70methodsec.repository.PersonRepository;
import ru.netology.data.l70methodsec.request.PersonByUserNameRequest;

import java.util.List;
import java.util.Optional;

/**
 * Person Controller
 *
 * <p>
 * <a href="https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html">How Method Security Works</a><br>
 * <a href="https://docs.spring.io/spring-security/reference/6.0/servlet/authorization/expression-based.html">Expression-Based Access Control</a><br>
 * </p>
 */
@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons/by-city")
    @Secured(BasePermission.READ)
    public List<Person> personsByCity(@Validated @NotEmpty String city) {
        return personRepository.findPersonsByCityOfLivingIgnoreCase(city);
    }

    @GetMapping("/persons/by-age-less-than")
    @RolesAllowed(BaseRole.ADMIN)
    public List<Person> personsByAgeLessThanOrderByAgeAsc(@Validated @Positive int age) {
        return personRepository.findPersonsByPrimaryKey_AgeLessThanOrderByPrimaryKey_AgeAsc(age);
    }

    /**
     * Person by name and surname
     * <p>
     * It is possible to generate final class to use Spring been logic and use bean alias (@AppPermission) instead of fully
     * qualified class name. But it not comfortable if we use several permission files with equal permission names</p>
     *
     * <p>
     *     <a href="https://stackoverflow.com/questions/17444856/using-static-variables-in-spring-annotations">Using static variables in Spring annotations</a>
     * </p>
     */
    @GetMapping("/person/by-name-and-surname")
    @PreAuthorize("hasAuthority(@AppPermission.DELETE) || hasRole(T(ru.netology.data.l70methodsec.model.BaseRole).ADMIN)")
    public Person personByNameAndSurname(@Validated PersonByNameAndSurnameRequest request) {
        return personRepository
                .findPersonByPrimaryKey_NameAndPrimaryKey_Surname(request.getName(), request.getSurname())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * For all authenticated users
     */
    @GetMapping("/person/some-other-method")
    public String someOtherMethod() {
        return "{\"test\": \"test\"}";
    }

    @GetMapping("/devops-or-user-message")
    @PreAuthorize("hasAnyRole('user', 'devops')")
    public String messageForUserOrDevopsRoles() {
        return "Message available only for USER or DEVOPS roles";
    }

    @GetMapping("/person/by-user-name")
    @PreAuthorize("#q.username == authentication.name")
    public Optional<Person> personByUsername(@Validated @P("q") PersonByUserNameRequest request) {
        return personRepository.findPerson1ByPrimaryKey_NameIgnoreCase(request.getUsername());
    }
}
