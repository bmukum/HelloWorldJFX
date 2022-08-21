package model;

public class Countries {
    private int id;
    private String country;

    public Countries(int id, String country){
        this.id = id;
        this.country = country;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return country;
    }
}
