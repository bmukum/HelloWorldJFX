package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevelDivisions {
    public static ObservableList<Divisions> getAllFirstLevelDivisions() throws SQLException {
        ObservableList<Divisions> divisionsList = FXCollections.observableArrayList();
        try {
            String sql = "Select * FROM first_level_divisions;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Divisions d = new Divisions(id, division, countryId);
                divisionsList.add(d);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisionsList;
    }
}
