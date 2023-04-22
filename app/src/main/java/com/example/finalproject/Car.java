package com.example.finalproject;

public class Car {
    private String make;
    private String price;
    private String model;
    private final int imageResource;

    public Car(String make, String price, String model, int imageResource){
        this.make = make;
        this.price = price;
        this.model = model;
        this.imageResource = imageResource;
    }

    public String getMake(){ return make; }

    public String getPrice(){ return price; }

    public String getModel(){ return model; }

    public int getImageResource(){ return imageResource; }
}
