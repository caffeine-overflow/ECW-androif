package com.example.dandavis.project;

public class Items {
    private int id_;
    private String name_;
    private double  price_;

    public Items(int id, String name, double price) {
        this.id_ = id;
        this.name_ = name;
        this.price_ = price;
    }

    public int getId_() {
        return id_;
    }

    public double getPrice_() {
        return price_;
    }

    public String getName_() {
        return name_;
    }
}
