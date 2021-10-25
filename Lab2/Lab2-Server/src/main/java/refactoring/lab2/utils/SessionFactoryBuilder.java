package refactoring.lab2.utils;


import refactoring.lab2.entities.DictionaryWord;
import refactoring.lab2.entities.Postfix;
import refactoring.lab2.entities.Prefix;
import refactoring.lab2.entities.Root;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Optional;
import java.util.Properties;

public class SessionFactoryBuilder {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory != null) return sessionFactory;
        try {

            Configuration configuration = new Configuration();
            Properties settings = new Properties();

            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, String.format("jdbc:postgresql://%s:%s/%s",
                    Optional.ofNullable(System.getenv("DB_HOST"))
                            .orElseThrow(() -> new IllegalArgumentException("Missing required env variable DB_HOST")),
                    Optional.ofNullable(System.getenv("DB_PORT"))
                            .orElseThrow(() -> new IllegalArgumentException("Missing required env variable DB_PORT")),
                    Optional.ofNullable(System.getenv("DB_NAME"))
                            .orElseThrow(() -> new IllegalArgumentException("Missing required env variable DB_NAME"))
            ));
            settings.put(Environment.USER,
                    Optional.ofNullable(System.getenv("DB_USER"))
                            .orElseThrow(() -> new IllegalArgumentException("Missing required env variable DB_USER")));
            settings.put(Environment.PASS,
                    Optional.ofNullable(System.getenv("DB_PASS"))
                            .orElseThrow(() -> new IllegalArgumentException("Missing required env variable DB_PASS")));
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(settings);

            configuration.addAnnotatedClass(DictionaryWord.class);
            configuration.addAnnotatedClass(Root.class);
            configuration.addAnnotatedClass(Prefix.class);
            configuration.addAnnotatedClass(Postfix.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}