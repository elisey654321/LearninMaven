package databaseController;

import DSL_FX.ClassGuide;
import DSL_FX.Guide;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

@Data
public abstract class getCurrentSessionFromConfig {
    private static Boolean createdGuide = false;

    public static void setCreatedGuide(Boolean createdGuide) {
        getCurrentSessionFromConfig.createdGuide = createdGuide;
    }

    public static Session getCurrentSessionFromConfig() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Configuration configure = new Configuration();
        if (createdGuide) {
            configure.addAnnotatedClass(ClassGuide.class);
        }
        configure.configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
