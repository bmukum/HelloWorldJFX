package database;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;

public abstract class DBCustomers {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customerList = FXCollections.observableArrayList();
        try{
            String sql  = "Select Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Division, first_level_divisions.Country_ID, Country FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String division = rs.getString("Division");
                String country = rs.getString("Country");
                Customers customer = new Customers(id, name, address, postalCode, phone, division, country);
                customerList.add(customer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public static int insert(String name, String address, String postalCode, String phone, int divisionId) throws SQLException{
        String sql = "INSERT into customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5,divisionId);

        int rowsInserted = ps.executeUpdate();
        return rowsInserted;

    }
}
