package controller;

import database.DBAppointments;
import database.DBContacts;
import database.DBCountries;
import database.DBFirstLevelDivisions;
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
import model.Customers;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class updateAppointment implements Initializable {
    public TextField idTF;
    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;
    public TextField typeTF;
    public ComboBox<Contacts> contactCB;
    public TextField customerTF;
    public TextField userTF;
    public TextField endTF;
    public TextField startTF;
    public DatePicker dateDP;

    private Appointments apptToModify = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadApptInfo(Appointments appt) throws SQLException {
        apptToModify = appt;
        String id = Integer.toString(appt.getId());
        idTF.setText(id);
        titleTF.setText(appt.getTitle());
        descTF.setText(appt.getDescription());
        locationTF.setText(appt.getLocation());
        typeTF.setText(appt.getType());

        contactCB.setItems(DBContacts.getAllContacts());
        int contactId = apptToModify.getContactId();
        for (Contacts c : contactCB.getItems()) {
            if (c.getId() == contactId){
                contactCB.setValue(c);
            }
        }

        dateDP.setValue(appt.getStart().toLocalDateTime().toLocalDate());
        startTF.setText(String.valueOf(apptToModify.getStart().toLocalDateTime().toLocalTime()));
        endTF.setText(String.valueOf(apptToModify.getEnd().toLocalDateTime().toLocalTime()));
        customerTF.setText(String.valueOf(apptToModify.getCustomerId()));
        userTF.setText(String.valueOf(apptToModify.getUserId()));
    }

    public void cancel (ActionEvent actionEvent) {
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

    public void save(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idTF.getText());
            String title = titleTF.getText();
            String description = descTF.getText();
            String location = locationTF.getText();
            Contacts c = (Contacts) contactCB.getSelectionModel().getSelectedItem();
            int contactId = c.getId();
            String type = typeTF.getText();
            int customerId = Integer.parseInt(customerTF.getText());
            int userId = Integer.parseInt(userTF.getText());

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

            //check overlap
            for (Appointments a : DBAppointments.getAllAppointments()){
                if (customerId == a.getCustomerId() && id != a.getId()){
                    if ((localStart.isAfter(a.getStart().toLocalDateTime()) || localStart.isEqual(a.getStart().toLocalDateTime())) &&  (localStart.isBefore(a.getEnd().toLocalDateTime()))){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setContentText("The provided time starts within a scheduled appointment. Please select another time");
                        alert.showAndWait();
                        return;
                    }
                    if (localEnd.isAfter(a.getStart().toLocalDateTime()) && ((localEnd.isBefore(a.getEnd().toLocalDateTime()) || localEnd.isEqual(a.getEnd().toLocalDateTime())))){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setContentText("The provided time overlaps with a scheduled appointment. Please select another time");
                        alert.showAndWait();
                        return;
                    }
                    if ((localStart.isBefore(a.getStart().toLocalDateTime()) || localStart.isEqual(a.getStart().toLocalDateTime())) && ((localEnd.isAfter(a.getEnd().toLocalDateTime())) || localEnd.isEqual(a.getEnd().toLocalDateTime()))){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setContentText("The provided time overlaps with a scheduled appointment. Please select another time");
                        alert.showAndWait();
                        return;
                    }
                }
            }

            Timestamp startTs = Timestamp.valueOf(localStart);
            Timestamp endTs = Timestamp.valueOf(localEnd);

            int rowsUpdate = DBAppointments.update(id, title, description, location,type, startTs, endTs, customerId, userId, contactId);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1100, 700);
            stage.setTitle("Main Screen");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}