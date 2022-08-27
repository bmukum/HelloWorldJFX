package model;

public class myReport {
    private int apptId;
    private String contact;


    public myReport(int apptId, String contact) {
        this.apptId = apptId;
        this.contact = contact;
    }


    public int getApptId() {
        return apptId;
    }

    public String getContact() {
        return contact;
    }


}

