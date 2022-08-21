package model;
public class Divisions {
    private int id;
    private String division;
    private int countryId;

    public Divisions(int id, String division, int countryId){
        this.id = id;
        this.division = division;
        this.countryId = countryId;
    }

    public int getId(){
        return id;
    }

    public String getDivision(){
        return division;
    }

    public int getCountryId(){
        return countryId;
    }
}
