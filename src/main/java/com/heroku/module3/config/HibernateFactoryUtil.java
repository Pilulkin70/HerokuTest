package com.heroku.module3.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HibernateFactoryUtil {
    private static EntityManager entityManager;

    public static void init() {
        final String databaseUriEnv = System.getenv("DATABASE_URL");
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "root";

        if (databaseUriEnv != null) {
            try {
                URI dbUri = new URI(databaseUriEnv);
                dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +
                        "?sslmode=require";
                username = dbUri.getUserInfo().split(":")[0];
                password = dbUri.getUserInfo().split(":")[1];
            } catch (URISyntaxException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> props = new HashMap<>();
        props.put("javax.persistence.jdbc.url", dbUrl);
        props.put("javax.persistence.jdbc.user", username);
        props.put("javax.persistence.jdbc.password", password);

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("persistence", props);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            init();
        }
        return entityManager;
    }
}
