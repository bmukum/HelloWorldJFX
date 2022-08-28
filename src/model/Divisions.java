package model;
/**
 * first level divisions class
 */

/**
 *
 * @author Brandon Mukum
 */
public class Divisions {
    private int id;
    private String division;
    private int countryId;

    public Divisions(int id, String division, int countryId){
        this.id = id;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * @return the id
     */
    public int getId(){
        return id;
    }

    /**
     * @return the division name
     */
    public String getDivision(){
        return division;
    }

    /**
     * @return the country id
     */
    public int getCountryId(){
        return countryId;
    }

    /**
     * @return the division name as a string object
     */
    @Override
    public String toString (){
        return division;
    }
}
