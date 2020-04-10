package edu.uga.cs.quiz_app.datamodel;

public class CountryContinent {
    private int id;
    private String country;
    private String continent;

    public CountryContinent() {
        this.id = -1;
        this.country = "";
        this.continent = "";
    }

    public CountryContinent(int id, String country,String continent){
        this.id = id;
        this.continent = continent;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
