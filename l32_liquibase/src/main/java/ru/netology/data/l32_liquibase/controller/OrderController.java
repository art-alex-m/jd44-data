package ru.netology.data.l32_liquibase.controller;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.data.l32_liquibase.repository.OrderJdbcRepository;

import java.util.List;

@RestController
public class OrderController {

    private final OrderJdbcRepository orderJdbcRepository;

    public OrderController(OrderJdbcRepository orderJdbcRepository) {
        this.orderJdbcRepository = orderJdbcRepository;
    }

    @GetMapping("/products/fetch-product")
    public List<String> fetchProduct(@Validated @NotEmpty String name) {
        return orderJdbcRepository.getProductNames(name);
    }
}
