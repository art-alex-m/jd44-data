package ru.netology.data.l31_springjdbc.repository;

import java.util.List;

public interface OrderRepository {
    List<String> getProductNames(String userName);
}
