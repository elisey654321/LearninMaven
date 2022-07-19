package DSL_FX;

import databaseController.getCurrentSessionFromConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Guide {

    private String nameGuide;
    private ArrayList<Detail> details = new ArrayList<>();

    public Guide() {
        Class<?> clazz = this.getClass();
        String nameClass = clazz.getName();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DataDSL.class)){
                String name = field.getName();
                String type = field.getType().toString();

                details.add(new Detail(name, type));
            }
        }
        setNameGuide(nameClass.replace("data.","").toLowerCase());

        createGuide();
//        addBasesDetail();
        initialize();

    }

    private void addBasesDetail() {
        Detail name = new Detail("name", String.class.getName());
        Detail id = new Detail("id", String.class.getName());

        this.details.add(name);
        this.details.add(id);
    }

    private void createGuide() {

        try {
            Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
            session.beginTransaction();
            List<Object[]> guides = session.createSQLQuery("select * from guide").list();
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

        getCurrentSessionFromConfig.setCreatedGuide(true);
    }

    protected void initialize(){
        Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
        session.beginTransaction();
        session.createSQLQuery(getQueryOnCreateTable(session)).executeUpdate();
        session.getTransaction().commit();
    }

    private String getQueryOnCreateTable(Session session) {
        String query = "";

        List<Object[]> guides = getGuides(session);
        
        if (guides.size() == 0) {
            query = getStringCreateNewTable(session);
        }else {
            query = getStringAlteTable(query, guides);
        }
      
        return query;        
    }

    private String getStringAlteTable(String query, List<Object[]> guides) {
        String alterTable = "";

        for (Detail detail : details) {
            boolean created = true;
            for (Object[] guide : guides) {
                String nameDetail = guide[2].toString();
                if (detail.getNameDetail().equals(nameDetail)){
                    created = false;
                }
            }
            if (created){
                alterTable += detail.getNameDetail() + " varchar,";
            }
        }

        if (!alterTable.equals("")){
            alterTable = alterTable.substring(0, alterTable.length() - 1);
            query = "ALTER TABLE " + nameGuide + " add " + alterTable;
        }
        return query;
    }

    private String getStringCreateNewTable(Session session) {
        String query;
        query = "create table " + nameGuide + "(";
        for (Detail detail : details) {
            String sDetail = detail.getNameDetail() + " varchar,";
            query += sDetail;

            session.save(ClassGuide.builder()
                    .name(nameGuide)
                    .typeClass(detail.getTypeClass())
                    .nameDetail(detail.getNameDetail()).build());

        }
        query = query.substring(0, query.length() - 1);
        query += ")";
        return query;
    }

    private List getGuides(Session session) {
        return session.createSQLQuery("SELECT code,name,namedetail,typeclass FROM guide WHERE name = :name")
                .setParameter("name", nameGuide)
                .addScalar("code", new IntegerType())
                .addScalar("name", new StringType())
                .addScalar("namedetail", new StringType())
                .addScalar("typeclass", new StringType())
                .list();
    }

}
