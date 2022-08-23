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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Divisions;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateCustomer implements Initializable {
    public ComboBox countryCB;
    public ComboBox stateTF;
    public TextField idTF;
    public TextField nameTF;
    public TextField addressTF;
    public TextField postalCodeTF;
    public TextField phoneTF;
    public Label stateLabel;

    private Customers customerToModify = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadCustomerInfo(Customers customer) throws SQLException {
        customerToModify = customer;
        String id = Integer.toString(customer.getId());
        idTF.setText(id);
        nameTF.setText(customer.getName());
        addressTF.setText(customer.getAddress());
        postalCodeTF.setText(customer.getPostalCode());
        phoneTF.setText(customer.getPhone());

        countryCB.setItems(DBCountries.getAllCountries());
        countryCB.setValue(customerToModify.getCountry());

        stateTF.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisions());
        stateTF.setValue(customerToModify.getDivision());

//        if (c.getId() == 1){
//            stateLabel.setText("State");
//        }
//        else if (c.getId() == 2){
//            stateLabel.setText("Constituent Country");
//        }
//        else {
//            stateLabel.setText("Province");
//        }
    }

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
