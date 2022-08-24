package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
    public int getContactId(){
        return contactId;
    }
    public Timestamp getStart(){
        return start;
    }
    public Timestamp getEnd(){
        return end;
    }

    public int getCustomerId(){
        return customerId;
    }
    public int getUserId(){
        return userId;
    }
}
