package controller;

import database.DBLogin;
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

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    public Label location;
    public TextField usernameTF;
    public TextField passwordTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ZoneId zoneId = ZoneId.systemDefault();
        location.setText(zoneId.getId());

    }

    public void Login(ActionEvent actionEvent) throws IOException {
//        try {
//
//            String username = usernameTF.getText();
//            String password = passwordTF.getText();
//            if (DBLogin.checkLogin(username, password) == true){
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
//                Parent root = loader.load();

//                Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                Scene addPartScene = new Scene(root, 1300, 690);
//                addPartStage.setTitle("Main Screen");
//                addPartStage.setScene(addPartScene);
//                addPartStage.show();
//            }
//            else if (DBLogin.checkLogin(username, password) == false){
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Warning Dialog");
//                alert.setContentText("Username or password is wrong. Please double-check!");
//                alert.showAndWait();
//                return;
//            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1100, 700);
            stage.setTitle("Main Screen");
            stage.setScene(scene);
            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
