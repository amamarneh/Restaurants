package com.am.restauarnts.data.model;

import java.util.List;

public class TopFoodEntity {
    private int id;
    private String name;
    private String image;
    private String description;
    private float price;
    private int category_id;
    private RestaurantEntity restaurant;
    private int parent_id;
    private List<FoodEntity> mandatory;
    private List<OptionEntity> options;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<FoodEntity> getMandatory() {
        return mandatory;
    }

    public void setMandatory(List<FoodEntity> mandatory) {
        this.mandatory = mandatory;
    }

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }
}
