package ru.netology.data.l41daohibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.netology.data.l41daohibernate.entity.Person;

import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get list of dwellers
     * <p>
     * <a href="https://www.baeldung.com/jpa-and-or-criteria-predicates">Combining JPA And/Or Criteria Predicates</a>
     * </p>
     *
     * @param city City
     * @return List of Persons or empty list
     */
    public List<Person> getPersonsByCity(String city) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> rootItem = criteriaQuery.from(Person.class);

        if (city != null && !city.isEmpty()) {
            criteriaQuery.where(builder.equal(rootItem.get("cityOfLiving"), city.toLowerCase()));
        }

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
