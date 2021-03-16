package com.imadelfetouh.authservice.dal.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionConfiguration {

    private static final SessionConfiguration sessionConfiguration = new SessionConfiguration();
    private final SessionFactory sessionFactory;

    private SessionConfiguration() {
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure().buildSessionFactory();
    }

    public static SessionConfiguration getInstance() {
        return (sessionConfiguration == null) ? new SessionConfiguration() : sessionConfiguration;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
