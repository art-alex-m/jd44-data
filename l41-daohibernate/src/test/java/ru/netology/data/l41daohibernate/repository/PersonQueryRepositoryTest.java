package ru.netology.data.l41daohibernate.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.netology.data.l41daohibernate.entity.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test that we have identical functionality with PersonRepository
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonQueryRepositoryTest extends PersonRepositoryTest {

    @Autowired
    @Qualifier("personRepository")
    PersonRepository personRepository;

    @Override
    @Autowired
    void setSut(@Qualifier("personQueryRepository") PersonRepository sut) {
        this.sut = sut;
    }

    @Test
    void weHaveCorrectSut() {
        assertInstanceOf(PersonQueryRepository.class, sut);
        assertNotEquals(PersonQueryRepository.class, personRepository.getClass());
    }


    @ParameterizedTest
    @ValueSource(ints = {30, 25, 12})
    void findPersonsByPrimaryKey_AgeLessThanOrderByPrimaryKey_AgeAsc(int age) {
        List<Person> expected = personRepository.findPersonsByPrimaryKey_AgeLessThanOrderByPrimaryKey_AgeAsc(age);

        List<Person> result = sut.findPersonsByPrimaryKey_AgeLessThanOrderByPrimaryKey_AgeAsc(age);

        assertIterableEquals(expected, result);
    }
}
