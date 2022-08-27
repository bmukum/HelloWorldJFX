package model;

public class typeReport {
    private String type;
    private int total;


    public typeReport(String type, int total) {
        this.type = type;
        this.total = total;
    }


    public String getType() {
        return type;
    }

    public int getTotal() {
        return total;
    }
}
