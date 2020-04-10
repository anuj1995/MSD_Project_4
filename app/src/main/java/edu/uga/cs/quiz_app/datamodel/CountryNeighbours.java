package edu.uga.cs.quiz_app.datamodel;

public class CountryNeighbours {

    private int id;
    private String country;
    private String neighbours;

    public CountryNeighbours() {
        this.id = -1;
        this.country = "";
        this.neighbours = "";
    }

    public CountryNeighbours(int id, String country,String neighbours){

        this.id = id;
        this.neighbours = neighbours;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
