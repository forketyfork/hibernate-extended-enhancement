package com.forketyfork.hibernate;

import org.h2.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtendedEnhancementTest {

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.getProperties().put("hibernate.connection.driver_class", Driver.class.getName());
        configuration.getProperties().put("hibernate.connection.url", "jdbc:h2:mem:test");
        configuration.getProperties().put("hibernate.connection.username", "sa");
        configuration.getProperties().put("hibernate.hbm2ddl.auto", "create-drop");
        configuration.getProperties().put("hibernate.show_sql", "true");
        configuration.addAnnotatedClass(Cat.class);
        return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build());
    }

    @Test
    public void test() {
        Session session = buildSessionFactory().openSession();
        try {

            session.beginTransaction();
            Cat cat = new Cat();
            cat.setName("Charlie");
            session.persist(cat);
            session.getTransaction().commit();
            session.clear();

            long id = cat.getId();

            session.beginTransaction();
            cat = session.get(Cat.class, id);
            cat.myCustomMethodToChangeData();
            session.getTransaction().commit();
            session.clear();

            session.beginTransaction();
            cat = session.get(Cat.class, id);
            assertEquals("Simba", cat.getName());
            session.getTransaction().commit();
            session.clear();

            session.beginTransaction();
            cat = session.get(Cat.class, id);
            new SneakyCatService().patACat(cat);
            session.getTransaction().commit();
            session.clear();

            session.beginTransaction();
            cat = session.get(Cat.class, id);
            assertEquals("Buddy", cat.getName());
            session.getTransaction().commit();
            session.clear();

        }
        catch (Exception exception) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw exception;
        }
        finally {
            session.close();
        }
    }

}
