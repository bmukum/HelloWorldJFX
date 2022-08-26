package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class reportsController implements Initializable {
    public TableView monthTable;
    public TableColumn mMonthCol;
    public TableColumn mTotalCol;
    public TableView typeTable;
    public TableColumn tTypeCol;
    public TableColumn tTotalColumn;
    public Label myReport;
    public TableView tytmTable;
    public TableColumn ContactIdCol;
    public TableColumn cTitleCol;
    public TableColumn cDescCol;
    public TableColumn cStartCol;
    public TableColumn cEndCol;
    public TableColumn cCIdCol;
    public ComboBox contactCB;

    public void back(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void contactSelection(ActionEvent actionEvent) {
    }

    public void viewByType(ActionEvent actionEvent) {
    }

    public void viewByMonth(ActionEvent actionEvent) {
    }
}
