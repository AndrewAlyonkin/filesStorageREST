package edu.alenkin.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * Util class for initialization hibernate session factory and for production hibernate sessions
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

@Slf4j
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    static {
        log.info("building serviceRegistry");
        final StandardServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            log.info("building sessionFactory");
            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
            log.error(e.getMessage());
        }
    }

    public static Session getSession(){
        log.info("open session");
        session = sessionFactory.openSession();
        return session;
    }

    public static void closeSession(){
        session.close();
    }
}
