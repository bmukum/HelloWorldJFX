package database;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;

public abstract class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {
        // declare observable list to be returned.
        try{
            String sql  = "Select Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Division, first_level_divisions.Country_ID, Country FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID;\n" +
                    "\n";
//            String sqlFirstLevel  = "Select * FROM first_level_diviso";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                int divisionId = rs.getInt("Division_ID");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return Observablelist;
    }
}
