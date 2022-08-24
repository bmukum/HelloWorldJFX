package controller;

import database.DBAppointments;
import database.DBContacts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
            LocalTime startLocalTime = LocalTime.parse(startTF.getText());
            LocalTime endLocalTime = LocalTime.parse(endTF.getText());
            LocalDateTime localStart = LocalDateTime.of(localDate, startLocalTime);
            LocalDateTime localEnd = LocalDateTime.of(localDate, endLocalTime);

            LocalTime openEST = LocalTime.of(8,00);
            LocalTime closeEST = LocalTime.of(22,00);
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

            ZoneId estZone = ZoneId.of("America/New_York");
            ZonedDateTime estOpenHour = ZonedDateTime.of(localDate, openEST, estZone);
            ZonedDateTime estCloseHour = ZonedDateTime.of(localDate, closeEST, estZone);

            ZonedDateTime estOpenToLocal = estOpenHour.withZoneSameInstant(localZoneId);
            ZonedDateTime estCloseToLocal = estCloseHour.withZoneSameInstant(localZoneId);

            if (startLocalTime.isBefore(estOpenToLocal.toLocalTime())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Please enter a time after 8:00 am EST.");
                alert.showAndWait();
                return;
            }

            if (endLocalTime.isAfter(estCloseToLocal.toLocalTime())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Please enter a time before 10:00 pm EST.");
                alert.showAndWait();
                return;
            }

            int rowsAdded = DBAppointments.

//            ZonedDateTime startZoned = localStart.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
//            ZonedDateTime startZonedEst = startZoned.withZoneSameInstant(ZoneId.of("America/New_York"));
//
//            ZonedDateTime endZoned = localEnd.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
//            ZonedDateTime endZonedEst = endZoned.withZoneSameInstant(ZoneId.of("America/New_York"));



        } catch (Exception e) {
            e.printStackTrace();
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
