package model;

/**
 * Countries class
 */

/**
 *
 * @author Brandon Mukum
 */
public class Countries {
    private int id;
    private String country;

    public Countries(int id, String country){
        this.id = id;
        this.country = country;
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
        return country;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the name as a pure string object
     */
    @Override
    public String toString (){
        return country;
    }
}
