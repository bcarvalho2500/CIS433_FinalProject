package com.example.finalproject.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_cars")
public class CustomCar {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int uid;
    @ColumnInfo(name = "make")
    private String make;
    @ColumnInfo(name = "model")
    private String model;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo(name = "year")
    private String year;
    @ColumnInfo(name = "wheels")
    private String wheels;
    @ColumnInfo(name = "trim")
    private String trim;
    @ColumnInfo(name = "cost")
    private String cost;

    public int getUid(){
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWheels() {
        return wheels;
    }

    public void setWheels(String wheels) {
        this.wheels = wheels;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
