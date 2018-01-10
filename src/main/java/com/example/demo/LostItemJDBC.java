package com.example.demo;

/**
 * Created by Adil Imam on 8/12/2017.
 */
public class LostItemJDBC {
    private int id;
    private String name;
    private String description;
    private String lost_location;
    private double reward_price;
    private String picture;
    private String owner_name;
    private String owner_phone;

    public LostItemJDBC(){

    }

    public LostItemJDBC(String name, String description, String lost_location, Double reward_price, String picture, String owner, String phone) {
        this.name = name;
        this.description = description;
        this.lost_location = lost_location;
        this.reward_price = reward_price;
        this.picture = picture;
        this.owner_name=owner;
        this.owner_phone=phone;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getLost_location() {
        return lost_location;
    }


    public void setLost_location(String lost_location) {
        this.lost_location = lost_location;
    }


    public double getReward_price() {
        return reward_price;
    }


    public void setReward_price(double reward_price) {
        this.reward_price = reward_price;
    }


    public String getPicture() {
        return picture;
    }


    public void setPicture(String picture) {
        this.picture = picture;
    }


    public String getOwner_name() {
        return owner_name;
    }


    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }


    public String getOwner_phone() {
        return owner_phone;
    }


    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }


    @Override
    public String toString() {
        return "LostItem [id=" + id + ", name=" + name + ", description=" + description + ", lost_location="
                + lost_location + ", reward_price=" + reward_price + ", picture=" + picture + "]";
    }



}
