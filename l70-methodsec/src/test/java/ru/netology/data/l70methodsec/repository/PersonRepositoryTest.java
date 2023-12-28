package ru.netology.data.l70methodsec.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.netology.data.l70methodsec.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {

    PersonRepository sut;

    public static Stream<Arguments> findPersonsByCityOfLivingIgnoreCase() {
        return Stream.of(
                Arguments.of("moscow", 2),
                Arguments.of("Moscow", 2),
                Arguments.of("spb", 0),
                Arguments.of("tombov", 1)
        );
    }

    @Autowired
    void setSut(@Qualifier("personRepository") PersonRepository sut) {
        this.sut = sut;
    }

    @ParameterizedTest
    @MethodSource
    void findPersonsByCityOfLivingIgnoreCase(String city, int expectedCount) {
        List<Person> result = sut.findPersonsByCityOfLivingIgnoreCase(city);

        assertNotNull(result);
        assertEquals(expectedCount, result.size());
    }


    @Test
    void findPersonByNameAndSurname() {
        String name = "Tom";
        String surname = "Cat";
        Optional<Person> expected = sut.findPersonByPrimaryKey_NameAndPrimaryKey_Surname(name, surname);

        Optional<Person> result = sut.findPersonByNameAndSurname(name, surname);

        assertEquals(expected, result);
    }

}
