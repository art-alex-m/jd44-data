package ru.netology.data.l32_liquibase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenUserNameHasOrders_thenReturnProductNames() {
        String userName = "alexey";
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
