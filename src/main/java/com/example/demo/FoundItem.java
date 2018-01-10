package com.example.demo;

public class FoundItem {

    private int id;
    private String finderName;
    private String finderPhone;
    private String finderEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFinderName() {
        return finderName;
    }

    public void setFinderName(String finderName) {
        this.finderName = finderName;
    }

    @Override
    public String toString() {
        return "FoundItem{" +
                "id=" + id +
                ", finderName='" + finderName + '\'' +
                ", finderPhone='" + finderPhone + '\'' +
                ", finderEmail='" + finderEmail + '\'' +
                ", name='" + name + '\'' +
                ", lostLocation='" + lostLocation + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    public String getFinderPhone() {
        return finderPhone;
    }

    public void setFinderPhone(String finderPhone) {
        this.finderPhone = finderPhone;
    }

    public String getFinderEmail() {
        return finderEmail;
    }

    public void setFinderEmail(String finderEmail) {
        this.finderEmail = finderEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLostLocation() {
        return lostLocation;
    }

    public void setLostLocation(String lostLocation) {
        this.lostLocation = lostLocation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public FoundItem(int id, String finderName, String finderPhone, String finderEmail, String name, String lostLocation, String picture) {
        this.id = id;
        this.finderName = finderName;
        this.finderPhone = finderPhone;
        this.finderEmail = finderEmail;
        this.name = name;

        this.lostLocation = lostLocation;
        this.picture = picture;
    }

    private String name;
    private String lostLocation;
    private String picture;


}
