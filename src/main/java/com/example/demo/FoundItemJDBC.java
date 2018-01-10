package com.example.demo;

/**
 * Created by Adil Imam on 1/9/2018.
 */
public class FoundItemJDBC {

    private String picture;
    private String itemName;
    private String lostLocation;
    private String finderName;
    private String finderPhone;
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FoundItemJDBC{" +
                "picture='" + picture + '\'' +
                ", itemName='" + itemName + '\'' +

                ", lostLocation='" + lostLocation + '\'' +
                ", finderName='" + finderName + '\'' +
                ", finderPhone='" + finderPhone + '\'' +
                ", finderEmail='" + finderEmail + '\'' +
                '}';
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setLostLocation(String lostLocation) {
        this.lostLocation = lostLocation;
    }

    public void setFinderName(String finderName) {
        this.finderName = finderName;
    }

    public void setFinderPhone(String finderPhone) {
        this.finderPhone = finderPhone;
    }

    public void setFinderEmail(String finderEmail) {
        this.finderEmail = finderEmail;
    }

    public FoundItemJDBC() {
    }

    private String finderEmail;

    public String getPicture() {
        return picture;
    }


    public void setPicture(String picture) {
        this.picture = picture;
    }
}
