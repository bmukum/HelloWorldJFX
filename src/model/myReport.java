package model;
/**
 * my report class
 */
public class myReport {
    private int apptId;
    private String contact;


    public myReport(int apptId, String contact) {
        this.apptId = apptId;
        this.contact = contact;
    }


    /**
     * @return the appointment id
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }


}

