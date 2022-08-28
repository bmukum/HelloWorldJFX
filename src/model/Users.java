package model;
/**
 * Users class
 */
/**
 *
 * @author Brandon Mukum
 */
public class Users {
    private int id;
    private String username;
    private String password;

    public Users(int id, String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the username
     */
    public String getUserName(){
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword(){
        return password;
    }
}
