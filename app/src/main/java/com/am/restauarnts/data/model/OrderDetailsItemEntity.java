package com.am.restauarnts.data.model;

import java.util.List;

public class OrderDetailsItemEntity {
    private int stock_id;
    private FoodEntity stock;
    private int quantity;
    private String message;
    private List<OptionEntity> options;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public FoodEntity getStock() {
        return stock;
    }

    public void setStock(FoodEntity stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }
}
