package com.ankita.carfaxtask.model;

public class MainScreen {
    private String vPhoto;
    private String year;
    private String make;
    private String model;
    private String trim;
    private String price;
    private String location;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getvPhoto() {
        return vPhoto;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setvPhoto(String vPhoto) {
        this.vPhoto = vPhoto;
    }


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMilage() {
        return milage;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String milage;

}
