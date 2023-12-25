package ru.netology.data.l41daohibernate.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonByNameAndSurnameRequest {
    @NotEmpty
    private final String name;

    @NotEmpty
    private final String surname;
}
