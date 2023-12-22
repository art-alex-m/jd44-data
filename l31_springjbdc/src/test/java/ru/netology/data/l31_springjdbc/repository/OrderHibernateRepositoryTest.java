package ru.netology.data.l31_springjdbc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * OrderHibernateRepositoryTest
 *
 * <p>
 * При тестировании JPA репозиториев лучше использовать следующую методику
 * <a href="https://courses.baeldung.com/courses/1295711/lectures/30127904">Lesson 7: Testing Spring Data Repositories</a>
 * </p>
 */
@SpringBootTest
class OrderHibernateRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    public static Stream<Arguments> getProductNames() {
        List<String> alexeyProductNames = List.of("cheesecake", "sesame", "beans", "wafer");

        return Stream.of(
                Arguments.of("alexey", alexeyProductNames),
                Arguments.of("Alexey", alexeyProductNames),
                Arguments.of("AleXey", alexeyProductNames),
                Arguments.of("Nobody", List.of()),
                Arguments.of("", List.of())
        );
    }

    @ParameterizedTest
    @MethodSource
    void getProductNames(String customerName, List<String> expectedNames) {
        OrderRepository sut = new OrderHibernateRepository().setEntityManager(entityManager);

        List<String> result = sut.getProductNames(customerName);

        assertIterableEquals(expectedNames, result);
    }
}
