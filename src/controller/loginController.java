package controller;

import database.DBLogin;
import database.DBUsers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller class for the login menu.
 */

/**
 *
 * @author Brandon Mukum
 */
public class loginController implements Initializable {
    public Label location;
    public TextField usernameTF;
    public TextField passwordTF;
    public Button exit;
    public Button loginButton;
    public Label loginTitle;
    public Label password;
    public Label username;

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        location.setText(zoneId.getId());
        ResourceBundle rb = ResourceBundle.getBundle("utilities/resource_bundle", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")){
            loginTitle.setText(rb.getString("Login"));
            username.setText(rb.getString("username"));
            password.setText(rb.getString("password"));
            loginButton.setText(rb.getString("Login"));
            exit.setText(rb.getString("Exit"));
        }
    }

    /**
     * @return id of the user
     * @param username is the username of the user logging in.
     */
    int id = 0;
    public int getUserId(String username) throws SQLException {
        for (Users u : DBUsers.getAllUsers()) {
            if (u.getUserName() == username) {
                int id = u.getId();
            }
        }
        return id;
    }

    /**
     * Method that gets the username and password and check for its correctness with the database. It either logs in the user if the username/password combination is correct or returns an error message if it's wrong.
     * It also records successful or failed login attempts to a file at the root of the project.
     */
    public void Login(ActionEvent actionEvent) throws IOException {
        try {
        ResourceBundle rb = ResourceBundle.getBundle("utilities/resource_bundle", Locale.getDefault());

        String file = "src/login_activity.txt";

        FileWriter fw = new FileWriter(file, true);
        PrintWriter outFile = new PrintWriter(fw);

        String username = usernameTF.getText();
        String password = passwordTF.getText();
        int id = DBLogin.getUserId(username, password);

        if (DBLogin.checkLogin(username, password) == true){

            outFile.println("User: " + username + " with id " + id + " login successful on " + LocalDate.now() + " at " + LocalTime.now());
            outFile.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
            Parent root = loader.load();
            mainController mc = loader.getController();
            mc.alertAppointment(id);

            Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene addPartScene = new Scene(root, 1300, 690);
            addPartStage.setTitle("Main Screen");
            addPartStage.setScene(addPartScene);
            addPartStage.show();
        }
        else if (DBLogin.checkLogin(username, password) == false) {
            outFile.println("User: " + username + " with id " + id + " login failed on " + LocalDate.now() + " at " + LocalTime.now());
            outFile.close();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText(rb.getString("loginerror"));alert.showAndWait();
            return;
        }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method quits the application when the exit button is clicked.
     */
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }
}

