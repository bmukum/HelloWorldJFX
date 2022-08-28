package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that works with the login screen
 */

/**
 *
 * @author Brandon Mukum
 */
public class DBLogin {

    /**
     *
     * @param username the username of the user trying to login
     * @param password of the authenticating user.
     * @return a boolean of true of false depending on whether the pair of credentials are correct.
     */
    public static boolean checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT User_Name, Password FROM users WHERE User_Name = ? AND Password = ?" ;
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        boolean checked = false;
        while(rs.next()){
            if (rs.getRow() > 0){
                System.out.println(rs.getRow());
                checked = true;
            }
            else {
                checked = false;
            }
        }
        return checked;
    }
}
