package model;

/**
 * Contact class
 */

/**
 *
 * @author Brandon Mukum
 */
public class Contacts {
    private int id;
    private String name;
    private String email;

    public Contacts(int id, String name,String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * @return the id
     */
    public int getId(){
        return id;
    }

    /**
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail(){
        return email;
    }

    /**
     * @return the name as a pure string
     */
    @Override
    public String toString (){
        return name;
    }
}
