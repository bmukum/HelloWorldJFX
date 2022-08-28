package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for accessing country records in the database
 */

/**
 *
 * @author Brandon Mukum
 */
public class DBCountries {

    /**
     * @return the list of all countries in the database
     */
    public static ObservableList<Countries> getAllCountries() throws SQLException {
        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        try {
            String sql = "Select * FROM Countries;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Country_ID");
                String name = rs.getString("Country");
                Countries c = new Countries(id, name);
                countryList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }
}
