package ru.netology.data.l31_springjdbc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.netology.data.l31_springjdbc.repository.OrderHibernateRepository;
import ru.netology.data.l31_springjdbc.repository.OrderJdbcRepository;
import ru.netology.data.l31_springjdbc.repository.OrderRepository;
import ru.netology.data.l31_springjdbc.repository.SqlQueryRepository;

@Configuration
public class L31SpringjdbcConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "repository.use-jdbc", havingValue = "true")
    public OrderRepository orderJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            SqlQueryRepository sqlQueryRepository) {
        return new OrderJdbcRepository(namedParameterJdbcTemplate, sqlQueryRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology", name = "repository.use-jdbc", havingValue = "false")
    public OrderRepository orderHibernateRepository(ApplicationContext context) {
        return context
                .getAutowireCapableBeanFactory()
                .createBean(OrderHibernateRepository.class);
    }
}
