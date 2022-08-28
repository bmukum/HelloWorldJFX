package model;
/**
 * month report class
 */

/**
 *
 * @author Brandon Mukum
 */
public class monthReport {
    private String month;
    private int total;


    public monthReport(String month, int total) {
        this.month = month;
        this.total = total;
    }


    /**
     * @return the month
     */
    public String getMonth() {

        return month;
    }

    /**
     * @return the total
     */
    public int getTotal() {

        return total;
    }
}
