package ru.netology.data.l70methodsec.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonByUserNameRequest {
    private final String username;
}
