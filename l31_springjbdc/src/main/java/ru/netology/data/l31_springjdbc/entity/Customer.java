package ru.netology.data.l31_springjdbc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Size(max = 30)
    @NotNull
    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
