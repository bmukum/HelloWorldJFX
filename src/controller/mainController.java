package controller;

import database.DBAppointments;
import database.DBContacts;
import database.DBCustomers;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Controller class for the main screen.
 */

/**
 *
 * @author Brandon Mukum
 */
public class mainController implements Initializable {
    public TableView customerTableView;
    public TableColumn cIdCol;
    public TableColumn cNameCol;
    public TableColumn cAddressCol;
    public TableColumn cCountryCol;
    public TableColumn cPostalCodeCol;
    public TableColumn cPhoneCol;
    public TableView apptTableView;
    public TableColumn aIdCol;
    public TableColumn aTitleCol;
    public TableColumn aDescCol;
    public TableColumn aLocationCol;
    public TableColumn aContactCol;
    public TableColumn aTypeCol;
    public TableColumn aStartCol;
    public TableColumn aEndCol;
    public TableColumn aCustIdCol;
    public TableColumn aUserIdCol;
    public TableColumn cDivisionCol;
    public ToggleGroup tg;

//    private static ObservableList<int> usDivisionIdArray
    /**
     * Initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customers> cList = DBCustomers.getAllCustomers();

        customerTableView.setItems(DBCustomers.getAllCustomers());

        cIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cAddressCol .setCellValueFactory(new PropertyValueFactory<>("address"));
        cPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        cCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        apptTableView.setItems(DBAppointments.getAllAppointments());

        aIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        aTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        aDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        aLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        aTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        aContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        aStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        aEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        aCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        aUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * Method that exits the application when the exit button is clicked.
     */
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void alertAppointment(int id){
        boolean found = false;
        for (Appointments a : DBAppointments.getAllAppointments()){
            long time = ChronoUnit.MINUTES.between(LocalTime.now(), a.getStart().toLocalDateTime().toLocalTime());
            if ((time > 0 && time <= 15) && id == a.getUserId()){
                found = true;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have an upcoming appointment. Appointment ID: " + a.getId() + " on " + a.getStart().toLocalDateTime().toLocalDate() + " at " + a.getStart().toLocalDateTime().toLocalTime());
                Optional<ButtonType> results = alert.showAndWait();
            }

        }
        if (!found) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Welcome! You  have no upcoming appointment.");
            Optional<ButtonType> results = alert.showAndWait();
        }
    }

    /**
     * This method deletes the selected customer from the database.
     */
    public void deleteCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        Customers selectedCustomer = (Customers) customerTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected Customer and all related appointments!");
        Optional<ButtonType> results = alert.showAndWait();

        if(results.isPresent() && results.get() == ButtonType.OK) {
            for (Appointments appt : DBAppointments.getAllAppointments()){
                if (appt.getCustomerId() == selectedCustomer.getId()){
                    DBAppointments.delete(appt.getId());
                }
            }
            DBCustomers.delete(selectedCustomer.getId());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1100, 700);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that takes the user to a screen that allows him/her to modify the attributes of a customer in the database.
     */
    public void updateCustomer(ActionEvent actionEvent) {
        Customers selectedCustomer = (Customers) customerTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select a customer to update.");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/updateCustomer.fxml"));
            Parent root = loader.load();
            updateCustomer ucc = loader.getController();
            ucc.loadCustomerInfo(selectedCustomer);

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Update Customer");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that takes the user to the menu that creates a new customer record..
     */
    public void addCustomer(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addCustomer.fxml"));
            Parent root = loader.load();

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Add Customer");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that opens up the window to add a customer appointment.
     */
    public void addAppt(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addAppointment.fxml"));
            Parent root = loader.load();

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Add Appointment");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handles the update button on the appointment table. When clicked, it takes the user to the screen that allows for appointment changes.
     */
    public void updateAppt(ActionEvent actionEvent) {
        Appointments selectedAppt = (Appointments) apptTableView.getSelectionModel().getSelectedItem();
        if (selectedAppt == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select an appointment to update.");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/updateAppointment.fxml"));
            Parent root = loader.load();
            updateAppointment uac = loader.getController();
            uac.loadApptInfo(selectedAppt);

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Update Customer");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that deletes the selected appointment.
     */
    public void deleteAppt(ActionEvent actionEvent) throws IOException, SQLException {
        Appointments selectedAppointment = (Appointments) apptTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select an appointment to delete!");
            alert.showAndWait();
            return;

        }
        else {
            System.out.println("Alert");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected appointment!");
            Optional<ButtonType> results = alert.showAndWait();

            if(results.isPresent() && results.get() == ButtonType.OK) {
                DBAppointments.delete(selectedAppointment.getId());
                Alert deleteAlert = new Alert(Alert.AlertType.WARNING);
                deleteAlert.setContentText("Appointment ID " + selectedAppointment.getId() + " of type " + selectedAppointment.getType() + " has been cancelled");
                deleteAlert.showAndWait();
            }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1100, 700);
        stage.setTitle("Main Screen");
        stage.setScene(scene);
        stage.show();
        }
    }

    /**
     * This method handles the radio button that displays appointments by month.
     * It also contains Lambda function 1 that iterates through the list of appointments, and get the appointments sorted by month, adds that resylts to a list that is then subsequently set on the table
     */
    public void monthRadioButton(ActionEvent actionEvent) {
        try {
            ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
            ObservableList<Appointments> appointmentsByMonth = FXCollections.observableArrayList();

            LocalDateTime thisMonth = LocalDateTime.now().minusMonths(1);
            LocalDateTime monthEnd = LocalDateTime.now().plusMonths(1);

            //LAMBDA 1
            if (allAppointments != null)
                apptTableView.setItems(DBAppointments.getAllAppointments().stream()
                        .filter(a -> a.getStart().toLocalDateTime().toLocalDate().getMonth() == LocalDate.now().getMonth())
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handles the radio button that displays appointments by week.
     * It also contains Lambda function 2.
     * The lambda loops through all appointments and gets those within the same week and sets that to the table.
     */
    public void weekRadioButton(ActionEvent actionEvent) {
        try {
            ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
            ObservableList<Appointments> appointmentsByWeek = FXCollections.observableArrayList();

            LocalDateTime thisWeek = LocalDate.now().with(WeekFields.ISO.getFirstDayOfWeek()).atStartOfDay();
            LocalDateTime wkEnd = thisWeek.plusWeeks(1);

            //LAMBDA 2
            if (allAppointments != null){

                apptTableView.setItems(DBAppointments.getAllAppointments().stream()
                        .filter(a -> a.getStart().toLocalDateTime().isAfter(thisWeek) && a.getStart().toLocalDateTime().isBefore(wkEnd))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The "See reports" button is handled by this method. It takes the user to the screen that allows him/her to see the reports.
     */
    public void seeReports(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/reports.fxml"));
            Parent root = loader.load();

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1100, 690);
            addPartStage.setTitle("Add Appointment");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is invoked by the view all radio button. When the button is clicked, it sets the appointment table with every appoinments that is not null.
     */
    public void viewAll(ActionEvent actionEvent) {
        try {
            ObservableList<Appointments> allAppointments = DBAppointments.getAllAppointments();
            if (allAppointments != null)
                for (Appointments a : allAppointments) {
                    apptTableView.setItems(allAppointments);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
