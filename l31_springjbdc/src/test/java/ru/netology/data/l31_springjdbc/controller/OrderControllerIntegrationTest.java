package ru.netology.data.l31_springjdbc.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderControllerIntegrationTest
 * <p>
 * <a href="https://www.baeldung.com/spring-tests-override-properties">Override Properties in Spring’s Tests</a><br>
 * <a href="https://www.baeldung.com/spring-test-property-source">A Quick Guide to @TestPropertySource</a>
 * </p>
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"netology.repository.use-jdbc=true"}
)
class OrderControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @ValueSource(strings = {"alexey", "Alexey", "AleXeY"})
    void whenUserNameHasOrders_thenReturnProductNames(String userName) {
        String[] expected = new String[]{"cheesecake", "sesame", "beans", "wafer"};
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost")
                .port(port)
                .path("/products/fetch-product")
                .queryParam("name", userName)
                .build()
                .toUri();

        ResponseEntity<String[]> result = restTemplate.getForEntity(url, String[].class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertInstanceOf(String[].class, result.getBody());
        assertArrayEquals(expected, result.getBody());
    }
}
