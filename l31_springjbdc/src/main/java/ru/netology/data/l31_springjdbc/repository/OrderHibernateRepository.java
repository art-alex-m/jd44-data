package ru.netology.data.l31_springjdbc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.netology.data.l31_springjdbc.entity.Customer;
import ru.netology.data.l31_springjdbc.entity.Order;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "netology", name = "repository.use-jdbc", havingValue = "false")
public class OrderHibernateRepository implements OrderRepository {

    private EntityManager entityManager;

    /**
     * Canonical EntityManager injection for controlled class testing
     *
     * <p>
     * <a href="https://docs.spring.io/spring-framework/reference/data-access/orm/jpa.html#orm-jpa-dao">Implementing DAOs Based on JPA: EntityManagerFactory and EntityManager</a>
     * <br>
     * <a href="https://www.baeldung.com/jpa-hibernate-persistence-context">JPA/Hibernate Persistence Context</a>
     * </p>
     *
     * @param entityManager EntityManager
     * @return self
     */
    @PersistenceContext
    public OrderHibernateRepository setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    @Override
    public List<String> getProductNames(String customerName) {
        return getOrdersByCustomerName(customerName).stream().map(Order::getProductName).toList();
    }

    /**
     * Get list of Orders selected by customer's name
     *
     * <p>
     * <a href="https://www.baeldung.com/jpa-and-or-criteria-predicates">Combining JPA And/Or Criteria Predicates</a><br>
     * <a href="https://www.baeldung.com/spring-jpa-joining-tables">Joining Tables With Spring Data JPA Specifications</a>
     * </p>
     *
     * @param customerName Name of the Customer
     * @return List of orders
     */
    public List<Order> getOrdersByCustomerName(String customerName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
        Root<Order> rootItem = criteriaQuery.from(Order.class);
        Join<Order, Customer> customerOrder = rootItem.join("customer", JoinType.INNER);

        criteriaQuery.where(
                builder.equal(
                        builder.lower(customerOrder.get("name")),
                        customerName.toLowerCase()
                ));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
