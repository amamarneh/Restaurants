package com.am.restauarnts.data.model;

import java.util.List;

public class CategoryEntity {
    private int id;
    private String name;
    private String image;
    private List<FoodEntity> food;


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

    public List<FoodEntity> getFood() {
        return food;
    }

    public void setFood(List<FoodEntity> food) {
        this.food = food;
    }
}
