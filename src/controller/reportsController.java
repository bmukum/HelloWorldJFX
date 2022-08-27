package controller;

import database.DBAppointments;
import database.DBContacts;
import database.DBCountries;
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
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

public class reportsController implements Initializable {
    public TableView monthTable;
    public TableColumn mMonthCol;
    public TableColumn mTotalCol;
    public TableView typeTable;
    public TableColumn tTypeCol;
    public TableColumn tTotalColumn;
    public Label myReport;
    public TableView<typeReport> tytmTable;
    public TableColumn ContactIdCol;
    public TableColumn cTitleCol;
    public TableColumn cDescCol;
    public TableColumn cStartCol;
    public TableColumn cEndCol;
    public TableColumn cCIdCol;
    public ComboBox contactCB;
    public TableView contactTableView;
    public TableColumn cTypeCol;
    public TableColumn CEndCol;
    public TableColumn cCustomerIdCol;
    public ToggleGroup tg;
    public TableView monthTableView;
    public TableColumn monthCol;
    public TableColumn contactEndCol;
    public TableColumn CustomerIdCol;

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
            Parent root = loader.load();

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1100, 700);
            addPartStage.setTitle("Main Screen");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Contacts> allContacts = DBContacts.getAllContacts();
            ObservableList<Divisions> divisions = FXCollections.observableArrayList();
            contactCB.setItems(allContacts);
            contactCB.setPromptText("Select the contact");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ObservableList<String> types = FXCollections.observableArrayList();
        ObservableList<String> distinctType = FXCollections.observableArrayList();

        ObservableList<typeReport> reportByType = FXCollections.observableArrayList();

        for (Appointments a : DBAppointments.getAllAppointments()) {
            String type = a.getType();
            if (!distinctType.contains(type))
                distinctType.add(type);
        }

        for (String type : distinctType) {
            String singleType = type;
            int total = Collections.frequency(distinctType, singleType);
            typeReport tr = new typeReport(singleType, total);
            reportByType.add(tr);
        }
        tytmTable.setItems(reportByType);
        tTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        tTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));

        //total appointments by month
        ObservableList<Month> allMonths = FXCollections.observableArrayList();
        ObservableList<Month> apptMonth = FXCollections.observableArrayList();
        ObservableList<monthReport> reportByMonth = FXCollections.observableArrayList();

        //Lambda 4
        DBAppointments.getAllAppointments().stream().map(a -> {
            return a.getStart().toLocalDateTime().getMonth();
        }).forEach(allMonths::add);

        allMonths.stream().filter(m -> {
            return !apptMonth.contains(m);
        }).forEach(apptMonth::add);

        for (Month m : allMonths) {
            int total = Collections.frequency(allMonths, m);
            String month = m.name();
            monthReport mr = new monthReport(month, total);
            reportByMonth.add(mr);
        }
        monthTableView.setItems(reportByMonth);
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        mTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
    }


    public void contactSelection(ActionEvent actionEvent) throws SQLException {
        ObservableList<Contacts> allContacts = DBContacts.getAllContacts();
        Contacts c = (Contacts) contactCB.getSelectionModel().getSelectedItem();
        int contactId = c.getId();

        ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();

        //Lambda 3
        ObservableList<Appointments> contactAppts = allAppts.filtered(a->{
            if (a.getContactId() == contactId)
                return true;
            System.out.println(a.getEnd());
            return false;
        });
        contactTableView.setItems(contactAppts);
        ContactIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        cTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        cDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        cStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        contactEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        cCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }
}
