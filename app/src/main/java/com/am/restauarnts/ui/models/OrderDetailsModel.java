package com.am.restauarnts.ui.models;

import java.util.Date;
import java.util.List;

public class OrderDetailsModel {
    private int id;
    private Date created;
    private Restaurant restaurant;
    private float totalPrice;
    private List<OrderDetailsItem> items;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetailsItem> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailsItem> items) {
        this.items = items;
    }
}
