package databaseController;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class MainDB {
    public static void main(String[] args) {
        System.out.println("MainDB Create");
        Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
        session.beginTransaction();

        NativeQuery query = session.createSQLQuery("select * from users_new");
        System.out.println(query.list().size());
        session.getTransaction().commit();

        System.out.println("All good");
    }


}
