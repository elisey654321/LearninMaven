package controllers;

import databaseController.getCurrentSessionFromConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.awt.*;

@Data
public class MainTemplate {

    @FXML
    private Button firstButton;

    @FXML
    private TableView tableGuide;

    public void onClickButton(ActionEvent actionEvent) {
        Session session = getCurrentSessionFromConfig.getCurrentSessionFromConfig();
        session.beginTransaction();

//        NativeQuery query = session.createSQLQuery("select * from users_new");
//        System.out.println(query.list().size());
//        session.getTransaction().commit();

    }

}
