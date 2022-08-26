package model;

public class monthReport {
    private String month;
    private int total;


    public monthReport(String month, int total) {
        this.month = month;
        this.total = total;
    }


    public String getMonth() {

        return month;
    }

    public int getTotal() {

        return total;
    }
}
