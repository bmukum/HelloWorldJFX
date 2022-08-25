package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLogin {
    public static boolean checkLogin(int id, String password) throws SQLException {
        String sql = "SELECT User_ID, Password FROM users WHERE User_ID = ? AND Password = ?" ;
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        boolean checked = false;
        while(rs.next()){
            if (rs.getRow() != 0){
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
