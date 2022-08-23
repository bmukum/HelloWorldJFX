package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();
        try {
            String sql = "Select * FROM Contacts;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts c = new Contacts(id, name, email);
                contactList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }
}
