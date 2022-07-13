package DSL_FX;

import databaseController.getCurrentSessionFromConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Guide {

    private String nameGuide;
    private ArrayList<Detail> details = new ArrayList<>();

    public Guide() {
        createGuide();

        String nameClass = this.getClass().getName();
        setNameGuide(nameClass.replace("data.","").toLowerCase());

        Detail name = new Detail("name", String.class.getName());
        Detail id = new Detail("id", String.class.getName());

        this.details.add(name);
        this.details.add(id);
    }

    private void createGuide() {

        try {
            Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
            session.beginTransaction();
            session.createSQLQuery("select * from guide").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
            session.beginTransaction();
            String sqlQuery = "create table guide(\n" +
                    "    name varchar PRIMARY KEY,\n" +
                    "    nameDetail varchar not null ,\n" +
                    "    typeClass varchar not null\n" +
                    ")";
            session.createSQLQuery(sqlQuery).executeUpdate();
            session.getTransaction().commit();
        }


    }

    protected void initialize(){
        Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
        session.beginTransaction();
        session.createSQLQuery(getQueryOnCreateTable()).executeUpdate();
        session.getTransaction().commit();
    }

    private String getQueryOnCreateTable() {
        String query = "create table " + nameGuide + "(";

        for (Detail detail: details) {
            String sDetail = detail.getNameDetail() + " varchar,";
            query += sDetail;
        }
        query = query.substring(0,query.length() - 1);
        query += ")";
        return query;
    }

}
