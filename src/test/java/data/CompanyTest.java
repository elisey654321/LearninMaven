package data;

import databaseController.getCurrentSessionFromConfig;
import org.hibernate.Session;
import org.junit.internal.builders.JUnit3Builder;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    @Test
    void test1(){
        try {
//            java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

            Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
            session.beginTransaction();
            session.createSQLQuery("drop table guide").executeUpdate();
            session.getTransaction().commit();

            Company company = new Company();
            System.out.println(company);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }
}