package databaseController;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class getCurrentSessionFromConfig {
    public static Session getCurrentSessionFromConfig() {
        Configuration configure = new Configuration();
        configure.configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
