package controller;

import database.DBContacts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contacts;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class addAppointment implements Initializable {
    public TextField idTF;
    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;
    public TextField typeTF;
    public ComboBox contactCB;
    public TextField customerTF;
    public TextField userTF;
    public TextField endTF;
    public TextField startTF;
    public DatePicker dateDP;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Contacts> allContacts = DBContacts.getAllContacts();
            contactCB.setItems(allContacts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void save(ActionEvent actionEvent) {
        try {
            String title = titleTF.getText();
            String description = descTF.getText();
            String location = locationTF.getText();
            Contacts c = (Contacts) contactCB.getSelectionModel().getSelectedItem();
            int contactId = c.getId();
            String type = typeTF.getText();
            LocalDate localDate = dateDP.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            String start = startTF.getText();
            Time


        }
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
            Parent root = loader.load();

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Main Screen");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
