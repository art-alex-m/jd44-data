package ru.netology.data.l70methodsec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netology.data.l70methodsec.entity.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Person.PrimaryKey> {

    List<Person> findPersonsByCityOfLivingIgnoreCase(String cityOfLiving);

    List<Person> findPersonsByPrimaryKey_AgeLessThanOrderByPrimaryKey_AgeAsc(int age);

    Optional<Person> findPersonByPrimaryKey_NameAndPrimaryKey_Surname(String name, String surname);

    /**
     * Query alias for findPersonByPrimaryKey_NameAndPrimaryKey_Surname()
     * Also this can be made dy default interface method
     *
     * <pre><code class="java">
     *
     * default Optional<Person> findPersonByNameAndSurname(String name, String surname) {
     *     return findPersonByPrimaryKey_NameAndPrimaryKey_Surname(String name, String surname);
     * }
     *
     * </code></pre>
     *
     * @param name    Name
     * @param surname Surname
     * @return Optional result
     */
    @Query("select p from Person p where p.primaryKey.name = :name and p.primaryKey.surname = :surname")
    Optional<Person> findPersonByNameAndSurname(String name, String surname);
}
