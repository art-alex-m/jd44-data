package ru.netology.data.l31_springjdbc.controller;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.data.l31_springjdbc.repository.OrderRepository;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/products/fetch-product")
    public List<String> fetchProduct(@Validated @NotEmpty String name) {
        return orderRepository.getProductNames(name);
    }
}
