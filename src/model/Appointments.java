package model;

public class Appointments {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private int contactId;
    private String date;
    private String startTime;
    private String endTime;
    private int customerId;
    private int userId;

    public Appointments(int id, String title,String description, String location, String type, int contactId, String date, String startTime, String endTime, int customerId, int userId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.contactId = contactId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    private String getDescription(){
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
    public String getDate(){
        return date;
    }
    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }
    public int getCustomerId(){
        return customerId;
    }
    private int getUserId(){
        return userId;
    }
}
