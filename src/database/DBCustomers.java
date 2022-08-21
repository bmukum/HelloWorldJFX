package database;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;

public abstract class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {
        try{
            String sql  = "Select * FROM customers";
            String sqlFirstLevel  = "Select * FROM first_level_diviso";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                int divisionId = rs.getInt("Division_ID");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAllCustomers();
    }
}
