package ru.netology.data.l41daohibernate.repository;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.data.l41daohibernate.entity.Person;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository sut;

    public static Stream<Arguments> getPersonsByCity() {
        return Stream.of(
                Arguments.of("moscow", 2),
                Arguments.of("Moscow", 2),
                Arguments.of("spb", 0),
                Arguments.of("tombov", 1),
                Arguments.of(null, 5),
                Arguments.of("", 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getPersonsByCity(String city, int expectedCount) {
        List<Person> result = sut.getPersonsByCity(city);

        assertNotNull(result);
        assertEquals(expectedCount, result.size());
    }
}
