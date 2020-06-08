package com.example.dandavis.project;

public class CartItem {
    public String quantity_;
    public String product_id_;
    public String image_id_;
    public String productName_;
    public double price_;

    public CartItem(String quantity_, String product_id, String image_id, String productName, double price) {
        this.quantity_ = quantity_;
        this.product_id_ = product_id;
        this.image_id_ = image_id;
        this.productName_=productName;
        this.price_ = price;
    }

    public String getQuantity() { return quantity_; }
    public String getProductId() {
        return product_id_;
    }
    public String getImageId() {
        return image_id_;
    }
    public String getProductName() {
        return productName_;
    }
    public double getPrice() {
        return price_;
    }

}
