package database;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;

/**
 * Class that manipulates records in the customer table.
 */

/**
 *
 * @author Brandon Mukum
 */
public abstract class DBCustomers {

    /**
     * @return the list of all customers
     */
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

    /**
     * @param name the name of the customer
     * @param address the customer's address
     * @param postalCode the postal code of the customer's address
     * @param phone the customer's phone number
     * @param divisionId the state of province of division of the customer
     * @return the number of rows inserted.
     */
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

    /**
     * @param name the name of the customer
     * @param id the id of the customer to modify
     * @param address the customer's address
     * @param postalCode the postal code of the customer's address
     * @param phone the customer's phone number
     * @param divisionId the state of province of division of the customer
     * @return the number of rows updated.
     */
    public static int update(int id,String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "UPDATE customers set Customer_Name = ? , Address = ? , Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.setInt(6, id);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated;
    }

    /**
     * @param id the id of the customer to delete.
     * @return the number of rows deleted.
     */
    public static int delete(int id) throws SQLException {
        String sql = "DELETE from customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);

        int rowsDeleted = ps.executeUpdate();
        return rowsDeleted;
    }
}
