package model;

/**
 * Reports by type
 */

/**
 *
 * @author Brandon Mukum
 */
public class typeReport {
    private Long month;
    private String type;
    private Long total;


    public typeReport(String type, Long month, Long total) {
        this.type = type;
        this.month = month;
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
    public Long getTotal() {
        return total;
    }
    public Long getMonth() {
        return month;
    }
}
