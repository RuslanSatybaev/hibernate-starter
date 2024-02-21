package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        User user1 = User
                .builder()
                .username("ivan3@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .build();
        log.info("User entity is in transient state, object: {}", user1);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            log.trace("Transaction is created, {}", transaction);

            session.saveOrUpdate(user1);
            log.trace("User is in persistent state: {}, session: {}", user1, session);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;

        }
        log.warn("User is in detached state {}", user1);
    }
}
