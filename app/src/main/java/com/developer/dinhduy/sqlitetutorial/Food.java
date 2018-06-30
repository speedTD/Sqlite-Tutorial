package com.developer.dinhduy.sqlitetutorial;

public class Food {
    private int ID;
    private  String name;
    private  byte [] picture;

    public Food(int ID, String name, byte[] picture) {

        this.ID = ID;
        this.name = name;
        this.picture = picture;
    }

    public Food() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
