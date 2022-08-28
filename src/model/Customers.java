package model;
/**
 * customer class
 */

/**
 *
 * @author Brandon Mukum
 */
public class Customers {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private String country;

    public Customers(int id, String name,String address, String postalCode, String phone, String division, String country){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName(){
        return name;
    }
    /**
     * @return the address
     */
    public String getAddress(){
        return address;
    }

    /**
     * @return the postal code
     */

    public String getPostalCode(){
        return postalCode;
    }
    /**
     * @return the phone number
     */
    public String getPhone(){
        return phone;
    }
    /**
     * @return the division id
     */
    public String getDivision() {
        return division;
    }
    /**
     * @return the country name
     */
    public String getCountry(){
        return country;
    }

    /**
     * @return the customer name as string object
     */
    @Override
    public String toString (){
        return name;
    }
}
