package ru.netology.data.l31_springjdbc.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "netology", name = "repository.use-jdbc", havingValue = "true")
public class OrderJdbcRepository implements OrderRepository {
    private static final String SELECT_PRODUCT_NAME_TMPL = "select-product-name.tmpl.sql";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SqlQueryRepository queryRepository;

    public OrderJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            SqlQueryRepository queryRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.queryRepository = queryRepository;
    }

    public List<String> getProductNames(String userName) {
        SqlParameterSource namedParams = new MapSqlParameterSource("user_name", userName);

        return namedParameterJdbcTemplate.queryForList(
                queryRepository.getSql(SELECT_PRODUCT_NAME_TMPL),
                namedParams,
                String.class
        );
    }
}
