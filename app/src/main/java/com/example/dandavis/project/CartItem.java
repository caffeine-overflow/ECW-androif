package com.example.dandavis.project;

public class CartItem {
    public String quantity_;
    public String product_id_;
    public String image_id_;

    public CartItem(String quantity_, String product_id, String image_id) {
        this.quantity_ = quantity_;
        this.product_id_ = product_id;
        this.image_id_ = image_id;
    }

    public String getQuantity() { return quantity_; }
    public String getProductId() {
        return product_id_;
    }
    public String getImageId() {
        return image_id_;
    }
}
