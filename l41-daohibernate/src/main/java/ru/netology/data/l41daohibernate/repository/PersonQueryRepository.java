package ru.netology.data.l41daohibernate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.netology.data.l41daohibernate.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonQueryRepository extends PersonRepository {
    @Override
    @Query("select p from Person p where p.cityOfLiving ilike :cityOfLiving")
    List<Person> findPersonsByCityOfLivingIgnoreCase(String cityOfLiving);

    @Override
    @Query("select p from Person p where p.primaryKey.age < :age order by p.primaryKey.age asc")
    List<Person> findPersonsByPrimaryKey_AgeLessThanOrderByPrimaryKey_AgeAsc(int age);

    /**
     * Necessary duplication findPersonByNameAndSurname() to complete the training task
     */
    @Override
    @Query("select p from Person p where p.primaryKey.name = :name and p.primaryKey.surname = :surname")
    Optional<Person> findPersonByPrimaryKey_NameAndPrimaryKey_Surname(String name, String surname);
}
