package database;

import com.sun.scenario.animation.shared.TimerReceiver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Class for the method that interacts with appointments table in the database.
 */

/**
 *
 * @author Brandon Mukum
 */
public abstract class DBAppointments {

    /**
     * This methods gets the list of all appoitments in the database.
     * @return the list of appointments.
     */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> apptList = FXCollections.observableArrayList();
        try{
            String sql  = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM appointments;";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end   = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments appt = new Appointments(id, title, description, location, type, contactId, start, end, customerId, userId);
                apptList.add(appt);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptList;
    }

    /**
     * This methods gets adds a new appointment into the database.
     * @param contactId the contact id of the contact in the appointment
     * @param description the description of the appoinment
     * @param title the title of the appointment
     * @param type the type of appointment
     * @param start the start date-time of the appointment
     * @param end the end time of the appointment
     * @param customerId the customer id associated with the appoitment
     * @param location the location of the appointment
     * @param userId the id of the user involved with the appointment.
     * @return the number of rows inserted.
     */
    public static int insert(String title, String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId) throws SQLException{
        String sql = "INSERT into appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5,start);
        ps.setTimestamp(6, end);
        ps.setInt(7,customerId);
        ps.setInt(8,userId);
        ps.setInt(9,contactId);

        int rowsInserted = ps.executeUpdate();
        return rowsInserted;
    }

    /**
     * This methods gets adds a new appointment into the database.
     * @param contactId the contact id of the contact in the appointment
     * @param description the description of the appointment
     * @param title the title of the appointment
     * @param type the type of appointment
     * @param endTs the send date-time of the appointment
     * @param startTs the start time of the appointment
     * @param customerId the customer id associated with the appoitment
     * @param location the location of the appointment
     * @param userId the id of the user involved with the appointment.
     * @param id the id of the appointment to update
     * @return the number of rows updated.
     */
    public static int update(int id,String title, String description, String location, String type, Timestamp startTs, Timestamp endTs, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title );
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startTs);
        ps.setTimestamp(6, endTs);
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9,contactId);
        ps.setInt(10, id);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated;
    }

    /**
     * @param id the id of the appointment to delete
     * @return the number of rows deleted.
     */
    public static int delete(int id) throws SQLException {
        String sql = "DELETE from appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);

        int rowsDeleted = ps.executeUpdate();
        return rowsDeleted;
    }
}
