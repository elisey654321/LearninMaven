package DSL_FX;

import databaseController.getCurrentSessionFromConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

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
                    "    code SERIAL,\n" +
                    "    name varchar not null,\n" +
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
        session.createSQLQuery(getQueryOnCreateTable(session)).executeUpdate();
        session.getTransaction().commit();
    }

    private String getQueryOnCreateTable(Session session) {
        List<Object[]> guides = session.createSQLQuery("SELECT code,name,namedetail,typeclass FROM guide WHERE name = :name")
                .setParameter("name",nameGuide)
                .addScalar("code", new IntegerType())
                .addScalar("name", new StringType())
                .addScalar("namedetail", new StringType())
                .addScalar("typeclass", new StringType())
                .list();
        for(Object[] guide : guides){
            guide[0].toString();
        }


//        String query = "create table " + nameGuide + "(";
//
//        for (Detail detail: details) {
//            String sDetail = detail.getNameDetail() + " varchar,";
//            query += sDetail;
//        }
//        query = query.substring(0,query.length() - 1);
//        query += ")";
//        return query;
        return "";
    }

}
