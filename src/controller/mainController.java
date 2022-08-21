package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Appointments;
import model.Customers;

import java.util.Optional;

public class mainController {
    public TableView customerTableView;
    public TableColumn cIdCol;
    public TableColumn cNameCol;
    public TableColumn cAddressCol;
    public TableColumn cStateCol;
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

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void deleteCustomer(ActionEvent actionEvent) {
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
            //Delete the customer
        }
    }

    public void updateCustomer(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/updateCustomer.fxml"));
            Parent root = loader.load();

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Update Customer");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void updateAppt(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/updateAppointment.fxml"));
            Parent root = loader.load();

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Update Appointment");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAppt(ActionEvent actionEvent) {
        Appointments selectedAppointment = (Appointments) apptTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select an appointment to delete.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected appointment!");
        Optional<ButtonType> results = alert.showAndWait();

        if(results.isPresent() && results.get() == ButtonType.OK) {
            //Delete the appointment
        }
    }

    public void monthRadioButton(ActionEvent actionEvent) {
        //implement the filter
    }

    public void weekRadioButton(ActionEvent actionEvent) {
        //implement the filter
    }
}
