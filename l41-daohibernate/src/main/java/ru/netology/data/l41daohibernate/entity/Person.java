package ru.netology.data.l41daohibernate.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "persons")
public class Person {
    @EmbeddedId
    @JsonUnwrapped
    private PrimaryKey primaryKey;

    @Column(length = 15)
    private String phoneNumber;

    @Column(name = "city_of_living", length = 100)
    private String cityOfLiving;

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class PrimaryKey implements Serializable {
        @Column(length = 20, nullable = false)
        private String name;

        @Column(length = 30, nullable = false)
        private String surname;

        private int age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PrimaryKey that)) return false;
            return age == that.age &&
                    Objects.equals(name, that.name) &&
                    Objects.equals(surname, that.surname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, surname, age);
        }
    }
}
