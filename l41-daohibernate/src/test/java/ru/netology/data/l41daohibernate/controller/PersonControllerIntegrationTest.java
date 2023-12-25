package ru.netology.data.l41daohibernate.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import ru.netology.data.l41daohibernate.entity.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @Test
    void givenMoscowCityName_whenFindPersonsByCity_thenSuccessList() {
        String city = "moscow";
        String uri = getUri("persons/by-city").queryParam("city", city).toUriString();

        ResponseEntity<Person[]> result = restTemplate.getForEntity(uri, Person[].class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().length);
    }


    @Test
    void givenAge30_whenFindPersonsByAgeLessThan_thenSuccessList() {
        int age = 30;
        String uri = getUri("/persons/by-age-less-than").queryParam("age", age).toUriString();
        List<String> expected = List.of("Alex Fox", "Ponny Shakal");

        ResponseEntity<Person[]> result = restTemplate.getForEntity(uri, Person[].class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(expected.size(), result.getBody().length);
        assertIterableEquals(
                expected,
                Arrays.stream(result.getBody())
                        .map((p) -> String.join(" ", p.getPrimaryKey().getName(), p.getPrimaryKey().getSurname()))
                        .toList()
        );
    }


    @ParameterizedTest
    @CsvSource({"Jerry, Mouse", "Tom, Cat"})
    void givenExistedNameAndSurname_whenFindPersonByNameAndSurname_thenSuccessPerson(String name, String surname) {
        String uri = getUri("/person/by-name-and-surname")
                .queryParam("name", name)
                .queryParam("surname", surname)
                .toUriString();

        ResponseEntity<Person> result = restTemplate.getForEntity(uri, Person.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(name, result.getBody().getPrimaryKey().getName());
        assertEquals(surname, result.getBody().getPrimaryKey().getSurname());
    }


    @Test
    void givenNotExistedNameAndSurname_whenFindPersonByNameAndSurname_thenNotFoundStatus() {
        String name = "Jerry";
        String surname = "Dog";
        String uri = getUri("/person/by-name-and-surname")
                .queryParam("name", name)
                .queryParam("surname", surname)
                .toUriString();

        ResponseEntity<Person> result = restTemplate.getForEntity(uri, Person.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }


    public UriComponentsBuilder getUri(String path) {
        return UriComponentsBuilder.fromHttpUrl("http://localhost")
                .port(port)
                .path(path);
    }

}
