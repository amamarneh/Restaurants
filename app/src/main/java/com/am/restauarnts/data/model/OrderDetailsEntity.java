package com.am.restauarnts.data.model;

import java.util.List;

public class OrderDetailsEntity {
    private int id;
    private String created;
    private RestaurantEntity restaurant;
    private float total_price;
    private int status;
    private List<OrderDetailsItemEntity> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public List<OrderDetailsItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailsItemEntity> items) {
        this.items = items;
    }
}
