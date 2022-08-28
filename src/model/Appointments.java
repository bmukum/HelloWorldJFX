package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * appointments class
 */

/**
 *
 * @author Brandon Mukum
 */
public class Appointments {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private int contactId;
    private Timestamp start;
    private Timestamp end;
    private int customerId;
    private int userId;

    public Appointments(int id, String title,String description, String location, String type, int contactId, Timestamp start, Timestamp end, int customerId, int userId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.contactId = contactId;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     *
     * @return the appointment id
     */
    public int getId(){
        return id;
    }

    /**
     *
     * @return the appointment title
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @return the appointment description
     */
    public String getDescription(){
        return description;
    }

    /**
     *
     * @return the appointment location
     */
    public String getLocation(){
        return location;
    }

    /**
     *
     * @return the appointment type
     */
    public String getType(){
        return type;
    }
    /**
     *
     * @return the contact id
     */
    public int getContactId(){
        return contactId;
    }

    /**
     *
     * @return the start date and time
     */
    public Timestamp getStart(){
        return start;
    }
    /**
     *
     * @return the end date time
     */
    public Timestamp getEnd(){
        return end;
    }

    /**
     *
     * @return the customer id
     */
    public int getCustomerId(){
        return customerId;
    }
    /**
     *
     * @return the user id
     */
    public int getUserId(){
        return userId;
    }
}
