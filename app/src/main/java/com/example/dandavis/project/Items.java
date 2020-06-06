package com.example.dandavis.project;

public class Items {
    public int id_;
    public String name_;
    public double  price_;

    public Items(int id_, String name, double price) {
        this.id_ = id_;
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
