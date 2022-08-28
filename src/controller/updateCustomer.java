package controller;

import database.DBCountries;
import database.DBCustomers;
import database.DBFirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contacts;
import model.Countries;
import model.Customers;
import model.Divisions;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller class for the update customer menu.
 */

/**
 *
 * @author Brandon Mukum
 */
public class updateCustomer implements Initializable {
    public ComboBox<Countries> countryCB;
    public ComboBox<Divisions> stateTF;
    public TextField idTF;
    public TextField nameTF;
    public TextField addressTF;
    public TextField postalCodeTF;
    public TextField phoneTF;
    public Label stateLabel;

    private Customers customerToModify = null;

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method fills the form with the selected customer's information.
     */
    public void loadCustomerInfo(Customers customer) throws SQLException {
        customerToModify = customer;
        String id = Integer.toString(customer.getId());
        idTF.setText(id);
        nameTF.setText(customer.getName());
        addressTF.setText(customer.getAddress());
        postalCodeTF.setText(customer.getPostalCode());
        phoneTF.setText(customer.getPhone());

        countryCB.setItems(DBCountries.getAllCountries());
        for (Countries c : countryCB.getItems()){
            if (c.getName() == customerToModify.getCountry()){
                countryCB.setValue(c);
            }
        }

        stateTF.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisions());
        for (Divisions d : stateTF.getItems()){
            if (d.getDivision() == customerToModify.getDivision()){
                stateTF.setValue(d);
            }
        }

        if (customerToModify.getCountry() == "U.S"){
            stateLabel.setText("State");

        }
        else if (customerToModify.getCountry() == "UK" ){
            stateLabel.setText("Constituent Country");
        }

        else {
            stateLabel.setText("Province");
        }
    }

    /**
     * This method collects all changes, runs series of error checks and updates the customer's record in the database if no error is detected.
     */
    public void save(ActionEvent actionEvent) {
        try {
            int id = customerToModify.getId();
            String name = nameTF.getText();
            String address = addressTF.getText();
            String postalCode = postalCodeTF.getText();
            String phone = phoneTF.getText();
            Countries country = (Countries) countryCB.getSelectionModel().getSelectedItem();

            Divisions division = (Divisions) stateTF.getSelectionModel().getSelectedItem();
            int divisionId = division.getId();

            int rowsUpdated = DBCustomers.update(id, name, address, postalCode, phone, divisionId);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1100, 700);
            stage.setTitle("Main Screen");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please put in valid values in each field.");
            alert.showAndWait();
        }
    }

    /**
     * This method returns the user to the main menu without saving any changes.
     */
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

    /**
     * This method gets the selected country and fills the division combobox with the appropriate list of divisions.
     */

    public void countryCB(ActionEvent actionEvent) {
        try {
            ObservableList<Divisions> divisionsChoice = FXCollections.observableArrayList();
            Countries c = (Countries) countryCB.getSelectionModel().getSelectedItem();
            if (c.getId() == 1){
                stateLabel.setText("State");

            }
            else if (c.getId() == 2){
                stateLabel.setText("Constituent Country");
            }

            else {
                stateLabel.setText("Province");
            }
            for (Divisions d : DBFirstLevelDivisions.getAllFirstLevelDivisions()){
                if (c.getId() == d.getCountryId()){
                    divisionsChoice.add(d);
                }
            }
            stateTF.setItems(divisionsChoice);
        }
        catch (Exception e){
        }
    }
}
