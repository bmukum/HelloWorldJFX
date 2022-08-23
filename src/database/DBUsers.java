package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {
    public static ObservableList<Users> getAllUsers() throws SQLException {
        ObservableList<Users> userList = FXCollections.observableArrayList();
        try {
            String sql = "Select * FROM Users;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users u = new Users(id, name, password);
                userList.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
}
