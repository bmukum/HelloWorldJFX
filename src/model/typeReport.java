package model;

/**
 * Reports by type
 */

/**
 *
 * @author Brandon Mukum
 */
public class typeReport {
    private String type;
    private int total;


    public typeReport(String type, int total) {
        this.type = type;
        this.total = total;
    }


    /**
     * @return the type of appointment
     */
    public String getType() {
        return type;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }
}
