package ru.netology.data.l31_springjdbc.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SqlQueryResourcesRepository implements SqlQueryRepository {
    private final Map<String, String> sqlTemplates = new HashMap<>();

    @Override
    public String getSql(String fileName) {
        if (sqlTemplates.containsKey(fileName)) {
            return sqlTemplates.get(fileName);
        }

        try (InputStream is = new ClassPathResource(fileName).getInputStream()) {
            String sql = new String(is.readAllBytes());
            sqlTemplates.replace(fileName, sql);
            return sql;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
